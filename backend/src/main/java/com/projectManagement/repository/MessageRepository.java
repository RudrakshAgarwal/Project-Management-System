package com.projectManagement.repository;

import com.projectManagement.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Messages, Long> {
    List<Messages> findByChatIdOrderByCreatedAtAsc(Long chatId);
}
