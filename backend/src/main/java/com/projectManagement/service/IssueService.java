package com.projectManagement.service;

import com.projectManagement.model.Issue;
import com.projectManagement.model.User;
import com.projectManagement.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

//    Optional<Issue> updateIssue(Long issueId, IssueRequest updateIssue, Long userId) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

//    List<Issue> getIssueByAssigneeId(Long assigneeId) throws Exception;

//    List<Issue> searchIssues(String title, String status, String priority, Long assigneeId) throws Exception;

//    List<User> getAssigneeForIssue(Long issueId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;
}
