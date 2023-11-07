package com.example.projectmanager.controllers;

import com.example.projectmanager.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/project/{projectId}/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;


    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PutMapping("/{assignmentId}")
    public ResponseEntity<String> approveAssignment(@PathVariable(value = "projectId") Long projectId,
                                            @PathVariable(value = "assignmentId") Long assignmentId,
                                            @RequestParam(value = "approve") Boolean approve) {
        try {
            Boolean approveResult = this.assignmentService.approveTaskAssigment(projectId, assignmentId, approve);
            if (approveResult) {
                return new ResponseEntity<>("Tasks successfully assigned", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Task assignment successfully deleted", HttpStatus.OK);
            }
        }
        catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
