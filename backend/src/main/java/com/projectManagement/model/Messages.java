package com.projectManagement.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    private Chat chat;

    @ManyToOne
    private User sender;

    public Messages() {
    }

    public Messages(Long id, String content, LocalDateTime createdAt, Chat chat, User sender) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.chat = chat;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
