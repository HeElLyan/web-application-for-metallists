package ru.he.services;

import ru.he.models.entities.Invitation;

public interface InvitationService {
    Invitation invite(Long userId, Long invitedUserId);
}
