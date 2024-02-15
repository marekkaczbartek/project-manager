package com.example.projectmanager.project;

import com.example.projectmanager.developer.Developer;
import com.example.projectmanager.task.Task;
import com.example.projectmanager.user.User;
import com.example.projectmanager.exceptions.ProjectNotFoundException;
import com.example.projectmanager.utils.DeveloperDetails;
import com.example.projectmanager.utils.ProjectDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> addNewProject(@RequestBody ProjectDetails projectDetails) {
        try {
            Project project = this.projectService.addNewProject(projectDetails);
            return new ResponseEntity<>(project, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return this.projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable(name = "id") Long id) {
        try {
            return this.projectService.getProjectById(id);
        }
        catch(ProjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),  e);
        }
    }

    @GetMapping("/{id}/users")
    public List<User> getUsersInProject(@PathVariable(value = "id") Long projectId) {
        return this.projectService.getUsersInProject(projectId);
    }

    @PostMapping("/{id}/user")
    public ResponseEntity<Developer> addDeveloperToProject(@PathVariable(value = "id") Long projectId,
                                                           @RequestBody DeveloperDetails developerDetails) {
        try {
            Developer developer = this.projectService.addDeveloperToProject(projectId, developerDetails);
            return new ResponseEntity<>(developer, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> getTasksInProject(@PathVariable(value = "id") Long projectId,
                                                                        @RequestParam(value = "spec", required = false) String specString) {
        try {
            List<Task> tasks;
            if (specString == null) {
                tasks = this.projectService.getAllTasksInProject(projectId);
            }
            else {
                tasks = this.projectService.getTasksInProjectBySpecialization(projectId, specString);
            }
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
