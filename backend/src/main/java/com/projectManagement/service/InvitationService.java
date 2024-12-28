package com.projectManagement.service;

import com.projectManagement.model.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {
    void sendInvitation(String email, Long projectId) throws MessagingException;

    Invitation acceptInvitation(String token, Long userId) throws Exception;

    String getTokenByUserMail(String userEmail) throws Exception;

    void deleteToken(String token);
}
