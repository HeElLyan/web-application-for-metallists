package ru.he.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.he.dto.RegDto;
import ru.he.models.enums.MetalGenre;
import ru.he.models.enums.instruments.InstrumentType;
import ru.he.models.enums.instruments.types.KeyBoard;
import ru.he.models.enums.instruments.types.Percussion;
import ru.he.models.enums.instruments.types.Strings;
import ru.he.models.enums.instruments.types.Vocals;
import ru.he.services.SignUpService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/signUp")
public class SignUpWithEntityController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("enumForMetalGenres", MetalGenre.values());
        model.addAttribute("enumForInstruments", InstrumentType.values());
        model.addAttribute("enumForVocals", Vocals.values());
        model.addAttribute("enumForGuitars", Strings.values());
        model.addAttribute("enumForPercussion", Percussion.values());
        model.addAttribute("enumForKeyboard", KeyBoard.values());
        return "signUp";
    }

    @PostMapping
    public String signUp(RegDto regDto) {
        signUpService.signUp(regDto);
        return "redirect:/signIn";
    }
}
