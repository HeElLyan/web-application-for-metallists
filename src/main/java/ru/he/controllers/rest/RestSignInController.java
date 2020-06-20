package ru.he.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.he.dto.AuthDto;
import ru.he.dto.UserDto;
import ru.he.services.SignInService;

//будет сразу тело, сконвертированное в json
@RestController
@Profile("rest")
public class RestSignInController {

    @Autowired
    private SignInService signInService;

    @RequestMapping(value = "/signIn", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> login(@RequestBody AuthDto authDto) {
        return ResponseEntity.ok(signInService.signIn(authDto));
    }
}
