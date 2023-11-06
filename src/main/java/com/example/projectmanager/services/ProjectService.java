package com.example.projectmanager.services;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.entities.Project;
import com.example.projectmanager.entities.Task;
import com.example.projectmanager.entities.User;
import com.example.projectmanager.exceptions.*;
import com.example.projectmanager.repositories.DeveloperRepository;
import com.example.projectmanager.repositories.ProjectRepository;
import com.example.projectmanager.repositories.TaskRepository;
import com.example.projectmanager.repositories.UserRepository;
import com.example.projectmanager.utils.DeveloperCredentials;
import com.example.projectmanager.utils.ProjectCredentials;
import com.example.projectmanager.utils.Specialization;
import com.example.projectmanager.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final DeveloperRepository developerRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          UserRepository userRepository,
                          DeveloperRepository developerRepository,
                          TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.developerRepository = developerRepository;
        this.taskRepository = taskRepository;
    }

    public Project addNewProject(ProjectCredentials projectCredentials) {
        String name = projectCredentials.name();
        List<DeveloperCredentials> users = projectCredentials.users();
        List<Developer> developers = new ArrayList<>();
        for (DeveloperCredentials userSpec : users) {
            Long userId = userSpec.userId();
            String specString = userSpec.specialization();
            Specialization specialization;
            if (Validation.isValidSpecialization(specString)) {
                specialization = Specialization.valueOf(specString);
            }
            else throw new InvalidSpecializationException("Invalid specialization");

            User user = userRepository.
                    findById(userId).
                    orElseThrow(() -> new UserNotFoundException("User not found"));
            developers.add(new Developer(user, specialization));
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
        return this.projectRepository.
                findById(id).
                orElseThrow(() -> new ProjectNotFoundException("Project not found"));
    }

    public List<User> getUsersInProject(Long projectId) {
        return this.userRepository.findAllByProjectId(projectId);
    }

    public Boolean isUserInProject(Long projectId, User user) {
        List<User> usersInProject = getUsersInProject(projectId);
        return usersInProject.contains(user);
    }

    public Boolean isTaskInProject(Project project, Task task) {
        List<Task> tasksInProject = this.taskRepository.findAllByProjectId(project.getId());
        return tasksInProject.contains(task);
    }

    public Developer addDeveloperToProject(Long projectId, DeveloperCredentials developerCredentials) {
        User user = this.userRepository.
                findById(developerCredentials.userId()).
                orElseThrow(() -> new UserNotFoundException("User not found"));
        Project project = this.projectRepository.
                findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        String specString = developerCredentials.specialization();
        Specialization specialization;
        if (Validation.isValidSpecialization(specString)) {
            specialization = Specialization.valueOf(specString);
        }
        else throw new InvalidSpecializationException("Invalid specialization");

        if (!isUserInProject(projectId, user)) {
            Developer developer = new Developer(user, project, specialization);
            return this.developerRepository.save(developer);
        }
        else {
            throw new UserAlreadyPresentException("User already present in the project");
        }
    }
}
