package com.example.projectmanager.controllers;

import com.example.projectmanager.entities.Project;
import com.example.projectmanager.exceptions.UserNotFoundException;
import com.example.projectmanager.services.ProjectService;
import com.example.projectmanager.utils.ProjectCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/project")
    public ResponseEntity<Project> addNewProject(@RequestBody ProjectCredentials projectCredentials) {
        try {
            Project project = this.projectService.addNewProject(projectCredentials);
            return new ResponseEntity<>(project, HttpStatus.CREATED);
        }
        catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist", e);
        }
    }

    @GetMapping("/project")
    public List<Project> getAllProjects() {
        return this.projectService.getAllProjects();
    }
}
