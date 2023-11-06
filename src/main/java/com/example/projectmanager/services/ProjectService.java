package com.example.projectmanager.services;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.entities.Project;
import com.example.projectmanager.entities.User;
import com.example.projectmanager.exceptions.ProjectNotFoundException;
import com.example.projectmanager.exceptions.UserAlreadyPresentException;
import com.example.projectmanager.exceptions.UserNotFoundException;
import com.example.projectmanager.repositories.DeveloperRepository;
import com.example.projectmanager.repositories.ProjectRepository;
import com.example.projectmanager.repositories.UserRepository;
import com.example.projectmanager.utils.DeveloperCredentials;
import com.example.projectmanager.utils.ProjectCredentials;
import com.example.projectmanager.utils.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final DeveloperRepository developerRepository;
    private final DeveloperService developerService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          DeveloperService developerService,
                          UserRepository userRepository,
                          DeveloperRepository developerRepository) {
        this.projectRepository = projectRepository;
        this.developerService = developerService;
        this.userRepository = userRepository;
        this.developerRepository = developerRepository;
    }

    public Project addNewProject(ProjectCredentials projectCredentials) {
        String name = projectCredentials.name();
        List<DeveloperCredentials> users = projectCredentials.users();
        List<Developer> developers = new ArrayList<>();
        for (DeveloperCredentials userSpec : users) {
            Long userId = userSpec.userId();
            Specialization spec = userSpec.specialization();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                developers.add(new Developer(user, spec));
            }
            else throw new UserNotFoundException("User not found");
        }

        Project project = new Project(name);
        project.setDevelopers(developers);
        projectRepository.save(project);
        for (Developer developer : developers) {
            developer.setProject(project);
            this.developerRepository.save(developer);
        }
        return project;
    }

    public Project addEmptyProject(String name) {
        return this.projectRepository.save(new Project(name));
    }

    public List<Project> getAllProjects() {
        return this.projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        Optional<Project> projectOptional = this.projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            return projectOptional.get();
        }
        else {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    public List<User> getUsersInProject(Long projectId) {
        return this.userRepository.findAllByProjectId(projectId);
    }

    public Boolean isUserInProject(Long projectId, User user) {
        List<User> usersInProject = getUsersInProject(projectId);
        return usersInProject.contains(user);
    }

    public Developer addDeveloperToProject(Long projectId, DeveloperCredentials developerCredentials) {
        Optional<User> user = this.userRepository.findById(developerCredentials.userId());
        Optional<Project> project = this.projectRepository.findById(projectId);
        Specialization specialization = developerCredentials.specialization();
        if (user.isPresent() && project.isPresent()) {
            if (!isUserInProject(projectId, user.get())) {
                Developer developer = new Developer(user.get(), project.get(), specialization);
                return this.developerRepository.save(developer);
            }
            else {
                throw new UserAlreadyPresentException("User already present in the project");
            }
        }
        else {
            if (user.isEmpty()) throw new UserNotFoundException("User not found");
            else throw new ProjectNotFoundException("Project not found");
        }
    }
}
