//package ru.he.controllers.chat;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
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
//import java.util.*;
//
//import static ru.he.dto.UserDto.from;
//
//@RestController
//public class MessagesWithRestController {
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
//    //    @GetMapping(name="/chat", value = "/chat", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
//    @GetMapping(value = "/messages", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
//    public ResponseEntity<List<Message>> getMessages(@RequestParam("band_id") Long bandId,
//                                                     @RequestParam(value = "w", required = false) String wait) {
//
//        if (wait != null) {
//            bandId = cashedIdPool.cashedOf(bandId);
//            synchronized (bandId) {
//                try {
//                    bandId.wait();
//                } catch (InterruptedException e) {
//                    throw new IllegalStateException(e);
//                }
//            }
//        }
//
//        List<Message> messageList = messageRepository.findAllByBandId(bandId);
//        return ResponseEntity.ok(messageList);
//    }
//
//    //    @PostMapping(name="/chat", value = "/chat", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
//    @PostMapping(value = "/messages", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
//    @ResponseStatus(value = HttpStatus.OK)
//    public ResponseEntity<Object> receiveMessage(@RequestParam("band_id") Long bandId, @RequestParam("text") String text, Authentication authentication) {
//
//        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
//        UserDto userBySession = from(details.getUser());
//
//        Optional<User> user = usersRepository.findById(userBySession.getId());
//        Optional<Band> band = bandRepository.findById(bandId);
//
//        Message message = Message.builder()
//                .band(band.get())
//                .user(user.get())
//                .text(text)
//                .createdAt(timeResolver.now())
//                .build();
//
//        messageRepository.save(message);
//
//        bandId = cashedIdPool.cashedOf(bandId);
//        synchronized (bandId) {
//            bandId.notifyAll();
//        }
//        System.out.println(message.getText());
//        return ResponseEntity.ok().build();
//    }
//}
