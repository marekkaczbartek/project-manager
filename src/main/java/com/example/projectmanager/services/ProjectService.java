package com.example.projectmanager.services;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.entities.Project;
import com.example.projectmanager.entities.User;
import com.example.projectmanager.repositories.DeveloperRepository;
import com.example.projectmanager.repositories.ProjectRepository;
import com.example.projectmanager.utils.ProjectCredentials;
import com.example.projectmanager.utils.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          DeveloperRepository developerRepository) {
        this.projectRepository = projectRepository;
        this.developerRepository = developerRepository;
    }


    public void addNewProject(ProjectCredentials projectCredentials) {
        Project newProject = new Project(projectCredentials.getName());
        this.projectRepository.save(newProject);
        for (Map.Entry<Long, Specialization> entry : projectCredentials.getUsers().entrySet()) {
            Long userId = entry.getKey();
            Specialization specialization = entry.getValue();
            System.out.println(newProject.getId());
            developerRepository.save(new Developer(userId, newProject.getId(), specialization));
        }
    }
}
