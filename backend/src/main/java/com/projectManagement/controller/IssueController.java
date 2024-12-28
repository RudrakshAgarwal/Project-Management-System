package com.projectManagement.controller;

import com.projectManagement.DTO.IssueDto;
import com.projectManagement.model.Issue;
import com.projectManagement.model.User;
import com.projectManagement.request.IssueRequest;
import com.projectManagement.response.MessageResponse;
import com.projectManagement.service.IssueService;
import com.projectManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    IssueService issueService;

    @Autowired
    UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueDto> createIssue(@RequestBody IssueRequest issueRequest, @RequestHeader("Authorization") String token) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);

        Issue createdIssue = issueService.createIssue(issueRequest, tokenUser);
        IssueDto issueDto = new IssueDto();
        issueDto.setId(createdIssue.getId());
        issueDto.setTitle(createdIssue.getTitle());
        issueDto.setDescription(createdIssue.getDescription());
        issueDto.setStatus(createdIssue.getStatus());
        issueDto.setProjectID(createdIssue.getProjectID());
        issueDto.setPriority(createdIssue.getPriority());
        issueDto.setDueDate(createdIssue.getDueDate());
        issueDto.setTags(createdIssue.getTags());
        issueDto.setProject(createdIssue.getProject());
        issueDto.setAssignee(createdIssue.getAssignee());

        return ResponseEntity.ok(issueDto);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId, @RequestHeader("Authorization") String token) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, tokenUser.getId());

        MessageResponse res = new MessageResponse();
        res.setMessage("Issue deleted successfully");

        return ResponseEntity.ok(res);
    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId, @PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(issueService.addUserToIssue(issueId, userId));
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable Long issueId, @PathVariable String status) throws Exception {
        return ResponseEntity.ok(issueService.updateStatus(issueId, status));
    }
}
