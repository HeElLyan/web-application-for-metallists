//package ru.he.controllers.chat;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import ru.he.components.resolvers.CashedIdPool;
//import ru.he.components.resolvers.TimeResolverLocalDateTime;
//import ru.he.dto.UserDto;
//import ru.he.models.entities.Band;
//import ru.he.models.entities.Message;
//import ru.he.models.entities.User;
//import ru.he.repositoriesJpa.BandRepository;
//import ru.he.repositoriesJpa.MessageRepository;
//import ru.he.repositoriesJpa.UsersRepository;
//import ru.he.security.details.UserDetailsImpl;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static ru.he.dto.UserDto.from;
//
//@Controller
//public class ChatWithRestController {
//
//    @Autowired
//    UsersRepository usersRepository;
//
//    @Autowired
//    MessageRepository messageRepository;
//
//    @Autowired
//    CashedIdPool cashedIdPool;
//
//    @Autowired
//    TimeResolverLocalDateTime timeResolver;
//
//    @Autowired
//    BandRepository bandRepository;
//
//    @GetMapping("/chatWithRest")
//    public ModelAndView getChatPage(@RequestParam("band_id") Long bandId,
//                                    @RequestParam(value = "w", required = false) String wait, Authentication authentication) {
//        List<Message> messageList = messageRepository.findAllByBandId(bandId);
//
//        ModelAndView modelAndView = new ModelAndView("chatWithRest");
//        if (messageList == null)
//            messageList = Collections.emptyList();
//        modelAndView.addObject("messageList", messageList);
//
//        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
//        UserDto userBySession = from(details.getUser());
//        Optional<User> user = usersRepository.findById(userBySession.getId());
//        modelAndView.addObject("user", user.get());
//
//        Optional<Band> band = bandRepository.findById(bandId);
//        modelAndView.addObject("band", band.get());
//
//        return modelAndView;
//    }
//}
//
