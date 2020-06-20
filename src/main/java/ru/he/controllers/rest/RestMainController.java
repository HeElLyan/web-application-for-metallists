package ru.he.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.he.dto.UserDto;
import ru.he.models.entities.Invitation;
import ru.he.models.entities.User;
import ru.he.models.enums.roles.UserInvitation;
import ru.he.repositoriesEntityManager.UsersRepository;
import ru.he.repositoriesJpa.FileInfoRepository;
import ru.he.security.details.UserDetailsImpl;
import ru.he.services.InvitationService;

import java.util.Optional;

import static ru.he.dto.UserDto.from;

@RestController
@Profile("rest")
public class RestMainController {

    @Autowired
    FileInfoRepository fileInfoRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    InvitationService invitationService;

    @RequestMapping(value = "/main/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(usersRepository.findByUsername(username).get());
    }

    @RequestMapping(value = "/main/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invitation> signIn(@PathVariable("username") String username, Authentication authentication) {
        Optional<User> user = usersRepository.findByUsername(username);
        Long userId = user.get().getId();

        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto userBySession = from(details.getUser());
        Long userBySessionId = userBySession.getId();
        user.get().setUserInvitation(UserInvitation.INVITED);
        return ResponseEntity.ok(invitationService.invite(userId, userBySessionId));
    }
}
