package com.projectManagement.controller;

import com.projectManagement.model.Chat;
import com.projectManagement.model.Messages;
import com.projectManagement.request.MessageRequest;
import com.projectManagement.service.MessageService;
import com.projectManagement.service.ProjectService;
import com.projectManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Messages> sendMessage(
            @RequestBody MessageRequest request
    ) throws Exception {
        Chat chat = projectService.getProjectById(request.getProjectId()).getChat();

        if (chat == null) {
            throw new Exception("Chat not found for project with id: " + request.getProjectId());
        }

        Messages sendMessage = messageService.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());

        return ResponseEntity.ok(sendMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Messages>> getMessagesByChatId(
            @PathVariable Long projectId
    ) throws Exception {
        List<Messages> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }

}
