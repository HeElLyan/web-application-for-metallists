package ru.he.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.he.models.entities.Invitation;
import ru.he.models.entities.User;
import ru.he.models.enums.InvitationState;
import ru.he.models.enums.State;
import ru.he.repositoriesJpa.InvitationRepository;
import ru.he.repositoriesJpa.UsersRepository;
import ru.he.services.ConfirmService;

@Service
public class ConfirmInvitationServiceImpl implements ConfirmService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Override
    public void confirm(String confirmId) {
        Invitation invitation = invitationRepository.findByConfirmId(confirmId)
                .orElseThrow(() -> new IllegalArgumentException("No such invitation"));
        invitation.setInvitationState(InvitationState.CONFIRMED);
        invitationRepository.save(invitation);
    }
}
