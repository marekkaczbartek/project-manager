package com.example.projectmanager.project;

import com.example.projectmanager.developer.Developer;
import com.example.projectmanager.developer.DeveloperRepository;
import com.example.projectmanager.exceptions.*;
import com.example.projectmanager.task.Task;
import com.example.projectmanager.task.TaskRepository;
import com.example.projectmanager.user.User;
import com.example.projectmanager.user.UserRepository;
import com.example.projectmanager.utils.DeveloperDetails;
import com.example.projectmanager.utils.ProjectDetails;
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

    public Project addNewProject(ProjectDetails projectDetails) {
        String name = projectDetails.name();
        List<DeveloperDetails> users = projectDetails.users();
        List<Developer> developers = new ArrayList<>();
        for (DeveloperDetails userSpec : users) {
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

    public Developer addDeveloperToProject(Long projectId, DeveloperDetails developerDetails) {
        User user = this.userRepository.
                findById(developerDetails.userId()).
                orElseThrow(() -> new UserNotFoundException("User not found"));
        Project project = this.projectRepository.
                findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        String specString = developerDetails.specialization();
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

    public List<Task> getTasksInProjectBySpecialization(Long projectId, String specString) {
        Project project = this.projectRepository.
                findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        String specStringCap = specString.toUpperCase();
        Specialization specialization;
        if (Validation.isValidSpecialization(specStringCap)) {
            specialization = Specialization.valueOf(specStringCap);
            return this.taskRepository.findAllByProjectIdAndSpecialization(projectId, specialization);
        }
        else throw new InvalidSpecializationException("Invalid specialization");
    }

    public List<Task> getAllTasksInProject(Long projectId) {
        Project project = this.projectRepository.
                findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        return this.taskRepository.findAllByProjectId(projectId);
    }
}
