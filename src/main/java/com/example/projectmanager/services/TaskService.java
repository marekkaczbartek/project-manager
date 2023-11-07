package com.example.projectmanager.services;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.entities.Project;
import com.example.projectmanager.entities.Task;
import com.example.projectmanager.exceptions.*;
import com.example.projectmanager.repositories.DeveloperRepository;
import com.example.projectmanager.repositories.ProjectRepository;
import com.example.projectmanager.repositories.TaskRepository;
import com.example.projectmanager.utils.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;
    private final ProjectService projectService;

    public TaskService(TaskRepository taskRepository,
                       ProjectRepository projectRepository,
                       DeveloperRepository developerRepository,
                       ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.developerRepository = developerRepository;
        this.projectService = projectService;
    }

    public Task addNewTask(Long projectId, TaskCredentials taskCredentials) {
        Task task;
        Project project = projectRepository.
                findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        String name = taskCredentials.name();
        String description = taskCredentials.description();
        String specString = taskCredentials.specialization();
        Long assignedToId = taskCredentials.assignedToId();
        Long createdById = taskCredentials.createdById();
        Integer estimation = taskCredentials.estimation();
        Date startDate = taskCredentials.dateRange().getStart();
        Date endDate = taskCredentials.dateRange().getEnd();
        Date createdAt = Date.valueOf(LocalDate.now());
        Specialization specialization;

        if (!Validation.isValidSpecialization(specString)) {
            throw new InvalidSpecializationException("Invalid specialization");
        }
        if (!Validation.isValidEstimation(estimation)) {
            throw new InvalidEstimationException("Invalid estimation");
        }

        Developer createdBy = developerRepository.
                findByIdAndProjectId(createdById, projectId).
                orElseThrow(() -> new UserNotFoundException("User not found in the project"));

        specialization = Specialization.valueOf(specString);

        if (assignedToId != null) {
            Developer assignedTo = developerRepository.
                    findByIdAndProjectId(assignedToId, projectId).
                    orElseThrow(() -> new UserNotFoundException("User not found in the project"));

            if (specialization.equals(assignedTo.getSpecialization())) {
                task = new Task(
                        project,
                        createdBy,
                        assignedTo,
                        name,
                        description,
                        TaskState.IN_PROGRESS,
                        specialization,
                        estimation,
                        createdAt,
                        startDate,
                        endDate);
            }
            else throw new InvalidSpecializationException("Developer and task specializations do not match");
        }
        else {
            task = new Task(
                    project,
                    createdBy,
                    null,
                    name,
                    description,
                    TaskState.OPEN,
                    specialization,
                    estimation,
                    createdAt,
                    startDate,
                    endDate);
        }
        return this.taskRepository.save(task);
    }

    public Task assignTaskToDeveloper(Long taskId, Long projectId, Long developerId) {
        Task task = this.taskRepository.
                findByIdAndProjectId(taskId, projectId).
                orElseThrow(() -> new TaskNotFoundException("Task not found in project"));
        Developer developer = this.developerRepository.
                findByIdAndProjectId(developerId, projectId).
                orElseThrow(() -> new UserNotFoundException("User not found in project"));

        if (Validation.isMatchingSpecialization(task, developer)) {
            task.setAssignedTo(developer);
            task.setTaskState(TaskState.IN_PROGRESS);
            return taskRepository.save(task);
        }
        else throw new InvalidSpecializationException("Developer and task specializations do not match");
    }

    public Task changeTaskState(Long projectId, Long taskId, String stateString) {
        Task task = this.taskRepository.
                findById(taskId).
                orElseThrow(() -> new TaskNotFoundException("Task not found"));
        Project project = this.projectRepository.
                findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        if (this.projectService.isTaskInProject(project, task)) {
            TaskState taskState;
            if (Validation.isValidState(stateString)) {
                taskState = TaskState.valueOf(stateString);
                if (taskState.equals(TaskState.OPEN)) task.setAssignedTo(null);
                task.setTaskState(taskState);
                return this.taskRepository.save(task);
            }
            else throw new InvalidStateException("Invalid state");
        }
        else {
            throw new TaskNotFoundException("Task not found in the project");
        }
    }

    public Task changeTaskDateRange(Long projectId, Long taskId, DateRange dateRange) {
        Task task = this.taskRepository.
                findById(taskId).
                orElseThrow(() -> new TaskNotFoundException("Task not found"));
        Project project = this.projectRepository.
                findById(projectId).
                orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        if (this.projectService.isTaskInProject(project, task)) {
            Date start = dateRange.getStart();
            Date end = dateRange.getEnd();

            if (start != null) {
                task.setStartDate(start);
            }
            if (end != null) {
                task.setEndDate(end);
            }
            return this.taskRepository.save(task);
        }
        else {
            throw new TaskNotFoundException("Task not found in the project");
        }
    }
}
