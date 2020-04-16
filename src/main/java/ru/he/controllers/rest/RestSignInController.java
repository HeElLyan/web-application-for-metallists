//package ru.he.controllers.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import ru.he.dto.AuthDto;
//import ru.he.dto.TokenDto;
//import ru.he.services.SignInWithTokenService;
//
////будет сразу тело, сконвертированное в json
//@RestController
//public class RestSignInController {
//
//    @Autowired
//    private SignInWithTokenService signInWithTokenService;
//
//    @PostMapping("/login")
//    public ResponseEntity<TokenDto> login(@RequestBody AuthDto authDto) {
//        return ResponseEntity.ok(signInWithTokenService.login(authDto));
//    }
//}
