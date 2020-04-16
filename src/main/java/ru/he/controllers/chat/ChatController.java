package ru.he.controllers.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.he.components.resolvers.CashedIdPool;
import ru.he.components.resolvers.TimeResolverLocalDateTime;
import ru.he.dto.MessageDto;
import ru.he.dto.UserDto;
import ru.he.models.entities.Band;
import ru.he.models.entities.Message;
import ru.he.models.entities.User;
import ru.he.repositoriesJpa.BandRepository;
import ru.he.repositoriesJpa.MessageRepository;
import ru.he.repositoriesJpa.UsersRepository;
import ru.he.security.details.UserDetailsImpl;

import java.util.*;

import static ru.he.dto.UserDto.from;

@Controller
public class ChatController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    CashedIdPool cashedIdPool;

    @Autowired
    TimeResolverLocalDateTime timeResolver;

    @Autowired
    BandRepository bandRepository;

//    @GetMapping(name="/chat", value = "/chat", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    @GetMapping("/chat")
    public ModelAndView getMessages(@RequestParam("band_id") Long bandId,
                                    @RequestParam(value = "w", required = false) String wait, Authentication authentication) {

        if (wait != null) {
            bandId = cashedIdPool.cashedOf(bandId);
            synchronized (bandId) {
                try {
                    bandId.wait();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        }

        List<Message> messageList = messageRepository.findAllByBandId(bandId);

        ModelAndView modelAndView = new ModelAndView("chat");
        if (messageList == null)
            messageList = Collections.emptyList();
        modelAndView.addObject("messageList", messageList);

        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto userBySession = from(details.getUser());
        Optional<User> user = usersRepository.findById(userBySession.getId());
        modelAndView.addObject("user", user.get());

        Optional<Band> band = bandRepository.findById(bandId);
        modelAndView.addObject("band", band.get());
//        modelAndView.addObject("post", postRepository.findById(bandId).orElseThrow(IllegalArgumentException::new));
        return modelAndView;
    }

//    @PostMapping(name="/chat", value = "/chat", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    @PostMapping("/chat")
    @ResponseStatus(value = HttpStatus.OK)
    public void receiveMessage(@RequestParam("band_id") Long bandId, @RequestParam("text") String text, Authentication authentication, @ModelAttribute("model") ModelMap model) {
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto userBySession = from(details.getUser());

        Optional<User> user = usersRepository.findById(userBySession.getId());
        Optional<Band> band = bandRepository.findById(bandId);

        Message message = Message.builder()
                .band(band.get())
                .user(user.get())
                .text(text)
                .createdAt(timeResolver.now())
                .build();

        messageRepository.save(message);

        bandId = cashedIdPool.cashedOf(bandId);
        synchronized (bandId) {
            bandId.notifyAll();
        }
        System.out.println(message.getText());
    }
}

