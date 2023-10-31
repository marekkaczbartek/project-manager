package com.example.projectmanager.controllers;

import com.example.projectmanager.services.ProjectService;
import com.example.projectmanager.utils.ProjectCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/project")
    public void addNewProject(@RequestBody ProjectCredentials projectCredentials) {
        this.projectService.addNewProject(projectCredentials);
    }
}
