package com.example.projectmanager.services;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.entities.User;
import com.example.projectmanager.repositories.DeveloperRepository;
import com.example.projectmanager.utils.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    @Autowired
    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }
    public void addDeveloperToProject(Developer developer) {
        Long projectId = developer.getProject().getId();
        Long userId = developer.getUser().getId();
        this.developerRepository.save(developer);
    }


}
