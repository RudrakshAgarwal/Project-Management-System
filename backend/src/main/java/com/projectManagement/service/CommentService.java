package com.projectManagement.service;

import com.projectManagement.model.Comments;

import java.util.List;

public interface CommentService {
    Comments createComment(Long issueId, Long userId, String content) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comments> findCommentByIssueId(Long issueId);
}
