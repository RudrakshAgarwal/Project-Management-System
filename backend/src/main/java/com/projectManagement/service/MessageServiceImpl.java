package com.projectManagement.service;

import com.projectManagement.model.Chat;
import com.projectManagement.model.Messages;
import com.projectManagement.model.User;
import com.projectManagement.repository.MessageRepository;
import com.projectManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public Messages sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User sender = userRepository.findById(senderId).orElseThrow(() -> new Exception("User not found with id: " + senderId));
        Chat chat = projectService.getProjectById(projectId).getChat();

        Messages messages = new Messages();
        messages.setContent(content);
        messages.setSender(sender);
        messages.setCreatedAt(LocalDateTime.now());

        Messages savedMessage = messageRepository.save(messages);
        chat.getMessages().add(savedMessage);

        return savedMessage;
    }

    @Override
    public List<Messages> getMessagesByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        List<Messages> messages = messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
        return messages;
    }
}
