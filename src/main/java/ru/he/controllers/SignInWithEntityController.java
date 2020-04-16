package ru.he.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.he.dto.AuthDto;
import ru.he.dto.UserDto;
import ru.he.services.SignInService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/signIn")
public class SignInWithEntityController {

    @Autowired
    private SignInService signInService;

    @GetMapping
    public String getSingIn(Authentication authentication, ModelMap model, HttpServletRequest request, HttpSession session, AuthDto authDto) {
        if (authentication != null) {
            return "redirect:/";
        }
        //если параметр error на странице присутствует, то выводится страница error
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        return "signIn";
    }
    //нужен рест
//    @PostMapping
//    public ResponseEntity<TokenDto> login(@RequestBody AuthDto authDto) {
//        return ResponseEntity.ok(signInService.signIn(authDto));
//    }

    @PostMapping
    public String signIn(AuthDto authDto) {
        UserDto user = signInService.signIn(authDto);
//        return "redirect:/main/" + user.getUsername() + "";
        return "redirect:/";
    }
}
