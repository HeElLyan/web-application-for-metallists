package ru.he.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.he.models.entities.Invitation;
import ru.he.models.enums.InvitationState;
import ru.he.models.enums.roles.UserInvitation;
import ru.he.repositoriesJpa.InvitationRepository;
import ru.he.repositoriesJpa.UsersRepository;
import ru.he.services.InvitationService;

import java.util.UUID;

@Component
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    InvitationRepository invitationRepository;

    @Override
    public Invitation invite(Long userId, Long invitedUserId) {
//        Long userId = invitationDto.getUserId();
//        Long invitedUserId = invitationDto.getInvitedUserId();

        String confirmId = UUID.randomUUID().toString();

        Invitation invitation = Invitation.builder()
                .user(usersRepository.findById(userId).get())
                .invitedUser(usersRepository.findById(invitedUserId).get())
                .userInvitation(UserInvitation.INVITED)
                .invitationState(InvitationState.UNCONFIRMED)
                .confirmId(confirmId)
                .build();

        invitationRepository.save(invitation);

        return invitation;
    }
}
