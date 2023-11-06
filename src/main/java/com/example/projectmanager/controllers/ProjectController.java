package com.example.projectmanager.controllers;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.entities.Project;
import com.example.projectmanager.entities.User;
import com.example.projectmanager.exceptions.ProjectNotFoundException;
import com.example.projectmanager.exceptions.UserNotFoundException;
import com.example.projectmanager.services.ProjectService;
import com.example.projectmanager.utils.DeveloperCredentials;
import com.example.projectmanager.utils.ProjectCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/project")
    public List<Project> getAllProjects() {
        return this.projectService.getAllProjects();
    }

    @GetMapping("/project/{id}")
    public Project getProjectById(@PathVariable(name = "id") Long id) {
        try {
            return this.projectService.getProjectById(id);
        }
        catch(ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),  e);
        }
    }

    @GetMapping("/project/{id}/users")
    public List<User> getUsersInProject(@PathVariable(value = "id") Long projectId) {
        return this.projectService.getUsersInProject(projectId);
    }

    @PostMapping("/project/{id}/user")
    public ResponseEntity<Developer> addDeveloperToProject(@PathVariable(value = "id") Long projectId,
                                                           @RequestBody DeveloperCredentials developerCredentials) {
        try {
            Developer developer = this.projectService.addDeveloperToProject(projectId, developerCredentials);
            return new ResponseEntity<>(developer, HttpStatus.CREATED);
        }
        catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
