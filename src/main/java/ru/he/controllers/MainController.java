package ru.he.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.he.dto.UserDto;
import ru.he.models.entities.FileInfo;
import ru.he.models.entities.User;
import ru.he.models.enums.roles.UserInvitation;
import ru.he.repositoriesJpa.FileInfoRepository;
import ru.he.repositoriesEntityManager.UsersRepository;
import ru.he.security.details.UserDetailsImpl;
import ru.he.services.InvitationService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static ru.he.dto.UserDto.from;

@Controller
//@Profile("mvc")
public class MainController {

    @Autowired
    FileInfoRepository fileInfoRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    InvitationService invitationService;

//    @GetMapping("/main")
//    public ModelAndView getAllUsers(@RequestParam(value = "username", required = false) String username) {
//        List<User> users = null;
//
//        if (username != null) {
//            users = usersRepository.findAllByUsername(username);
//        } else {
//            users = usersRepository.findAll();
//        }
//        ModelAndView modelAndView = new ModelAndView("main");
//        modelAndView.addObject("users", users);
//        return modelAndView;
//    }

    @GetMapping("/main/{username}")
    public String getUserById(@PathVariable("username") String username, @ModelAttribute("model") ModelMap modelMap, Authentication authentication) {
        Optional<User> user = usersRepository.findByUsername(username);
        modelMap.addAttribute("user", user.get());

        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto userBySession = from(details.getUser());
        modelMap.addAttribute("userBySession", userBySession);

        String email = user.get().getEmail();
        List<FileInfo> fileInfos = fileInfoRepository.findAllByEmail(email);
        modelMap.addAttribute("files", fileInfos);
        return "user";
    }

    @PostMapping("/main/{username}")
    public String invite(@PathVariable("username") String username, @ModelAttribute("model") ModelMap modelMap, Authentication authentication) {
        Optional<User> user = usersRepository.findByUsername(username);
        Long userId = user.get().getId();

        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto userBySession = from(details.getUser());
        Long userBySessionId = userBySession.getId();

        invitationService.invite(userId, userBySessionId);

        user.get().setUserInvitation(UserInvitation.INVITED);

        return "redirect:/main/{username}";
    }

}
