package com.projectManagement.service;

import com.projectManagement.model.Comments;
import com.projectManagement.model.Issue;
import com.projectManagement.model.User;
import com.projectManagement.repository.CommentRepository;
import com.projectManagement.repository.IssueRepository;
import com.projectManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comments createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);

        Optional<User> userOptional = userRepository.findById(userId);

        if (issueOptional.isEmpty())
            throw new Exception("Issue not found with id: " + issueId);

        if (userOptional.isEmpty())
            throw new Exception("User not found with id: " + userId);

        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comments comment = new Comments();
        comment.setUser(user);
        comment.setIssue(issue);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comments savedComment = commentRepository.save(comment);
        issue.getComments().add(savedComment);

        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comments> commentOptional = commentRepository.findById(commentId);

        Optional<User> userOptional = userRepository.findById(userId);

        if (commentOptional.isEmpty())
            throw new Exception("Comment not found with id: " + commentId);

        if (userOptional.isEmpty())
            throw new Exception("User not found with id: " + userId);

        Comments comment = commentOptional.get();
        User user = userOptional.get();

        if (comment.getUser().equals(user))
            commentRepository.delete(comment);
        else
            throw new Exception("User doesn't have permission to delete this comment !");
    }

    @Override
    public List<Comments> findCommentByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }
}
