package com.projectManagement.repository;

import com.projectManagement.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByIssueId(Long issueId);


}
