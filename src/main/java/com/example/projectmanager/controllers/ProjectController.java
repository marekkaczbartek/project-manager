package com.example.projectmanager.controllers;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.entities.Project;
import com.example.projectmanager.entities.User;
import com.example.projectmanager.services.ProjectService;
import com.example.projectmanager.utils.ProjectCredentials;
import com.example.projectmanager.utils.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/project")
    public ResponseEntity<Project> addNewProject(@RequestBody ProjectCredentials projectCredentials) {
        Project project = this.projectService.addNewProject(projectCredentials);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @PostMapping("/project/empty")
    public void addEmptyProject(@RequestBody String name) {
        this.projectService.addEmptyProject(name);
    }

    @GetMapping("/project")
    public List<Project> getAllProjects() {
        return this.projectService.getAllProjects();
    }
}
