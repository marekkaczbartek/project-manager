package com.example.projectmanager.services;

import com.example.projectmanager.entities.*;
import com.example.projectmanager.exceptions.AssignmentNotFoundException;
import com.example.projectmanager.exceptions.ProjectNotFoundException;
import com.example.projectmanager.repositories.AssignmentRepository;
import com.example.projectmanager.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final ProjectRepository projectRepository;
    private final ProjectService projectService;
    private final TaskService taskService;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository, ProjectRepository projectRepository, ProjectService projectService, TaskService taskService) {
        this.assignmentRepository = assignmentRepository;
        this.projectRepository = projectRepository;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    public Boolean approveTaskAssigment(Long projectId, Long assignmentId, Boolean approve) {
        Project project = this.projectRepository.
                findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        Assignment assignment = this.assignmentRepository.
                findById(assignmentId).
                orElseThrow(() -> new AssignmentNotFoundException("Assigment not found"));
        if (this.projectService.isAssignmentInProject(project, assignment)) {
            if (approve) {
                for (TaskDevPair pair : assignment.getAssignments()) {
                    Task task = pair.getTask();
                    Developer developer = pair.getDeveloper();
                    this.taskService.assignTaskToDeveloper(task.getId(), projectId, developer.getId());
                }
                return true;
            }
            else {
                this.assignmentRepository.delete(assignment);
                return false;
            }
        }
        else throw new AssignmentNotFoundException("Assignment not found in project");
    }
}
