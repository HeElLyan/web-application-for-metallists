package ru.he.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.he.services.ConfirmService;

@Controller
public class ConfirmInvitationController {

    @Qualifier("confirmInvitationServiceImpl")
    @Autowired
    private ConfirmService confirmService;

    @GetMapping("/confirmInvitation/{confirm-id}")
    public ModelAndView confirm(@PathVariable("confirm-id") String confirmId) {
        System.out.println("confirm" + confirmId);
        try {
            confirmService.confirm(confirmId);
        } catch (IllegalArgumentException e) {
            return new ModelAndView("confirmUnsuccess");
        }
        return new ModelAndView("confirmSuccess");
    }

}
