package com.projectManagement.service;

import com.projectManagement.model.Messages;

import java.util.List;

public interface MessageService {
    Messages sendMessage(Long senderId, Long projectId, String content) throws Exception;

    List<Messages> getMessagesByProjectId(Long projectId) throws Exception;
}
