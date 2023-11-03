package com.example.projectmanager.controllers;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.ReadPendingException;

@RestController
public class DeveloperController {
    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping("/developer")
    public ResponseEntity<Developer> addDeveloperToProject(@RequestBody Developer developer) {
        return new ResponseEntity<>(developerService.createDeveloper(developer), HttpStatus.CREATED);
    }
}
