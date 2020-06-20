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
    private UsersRepository usersRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CashedIdPool cashedIdPool;

    @Autowired
    private TimeResolverLocalDateTime timeResolver;

    @Autowired
    private BandRepository bandRepository;

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

        User user = usersRepository.findById(userBySession.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        modelAndView.addObject("user", user);

        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new IllegalArgumentException("Band not found"));
        modelAndView.addObject("band", band);
        return modelAndView;
    }

//    @PostMapping(name="/chat", value = "/chat", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    @PostMapping("/chat")
    @ResponseStatus(value = HttpStatus.OK)
    public void receiveMessage(@RequestParam("band_id") Long bandId, @RequestParam("text") String text, Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto userBySession = from(details.getUser());

        User user = usersRepository.findById(userBySession.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new IllegalArgumentException("Band not found"));

        Message message = Message.builder()
                .band(band)
                .user(user)
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

