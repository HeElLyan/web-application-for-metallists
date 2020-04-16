//package ru.he.controllers.chat;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import ru.he.dto.UserDto;
//import ru.he.models.entities.Band;
//import ru.he.repositoriesJpa.BandRepository;
//import ru.he.security.details.UserDetailsImpl;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static ru.he.dto.UserDto.from;
//
//@Controller
//public class TestChatController {
//
//    @Autowired
//    BandRepository bandRepository;
//
//    @GetMapping("/testChat")
//    public String getChatPage(Model model) {
//        model.addAttribute("pageId", UUID.randomUUID().toString());
//        return "testChat";
//    }
//}
