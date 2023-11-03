package com.example.projectmanager.controllers;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeveloperController {
    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping("/developers")
    public void addDeveloperToProject(@RequestBody Developer developer) {
        this.developerService.addDeveloperToProject(developer);
    }
}
