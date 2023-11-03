package com.example.projectmanager.services;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.entities.Project;
import com.example.projectmanager.entities.User;
import com.example.projectmanager.exceptions.UserNotFoundException;
import com.example.projectmanager.repositories.DeveloperRepository;
import com.example.projectmanager.repositories.ProjectRepository;
import com.example.projectmanager.repositories.UserRepository;
import com.example.projectmanager.utils.DeveloperCredentials;
import com.example.projectmanager.utils.ProjectCredentials;
import com.example.projectmanager.utils.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final DeveloperService developerService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          DeveloperService developerService,
                          UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.developerService = developerService;
        this.userRepository = userRepository;
    }


    public Project addNewProject(ProjectCredentials projectCredentials) {
        String name = projectCredentials.getName();
        List<DeveloperCredentials> users = projectCredentials.getUsers();
        List<Developer> developers = new ArrayList<>();
        for (DeveloperCredentials userSpec : users) {
            String userId = userSpec.getUserId();
            Specialization spec = userSpec.getSpecialization();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                developers.add(new Developer(user, spec));
            }
            else throw new UserNotFoundException("User doesnt exist");
        }

        Project project = new Project(name);
        project.setDevelopers(developers);
        projectRepository.save(project);
        for (Developer developer : developers) {
            developer.setProject(project);
            developerService.createDeveloper(developer);
        }
        return project;
    }

    public Project addEmptyProject(String name) {
        return this.projectRepository.save(new Project(name));
    }

    public List<Project> getAllProjects() {
        return this.projectRepository.findAll();
    }
}
