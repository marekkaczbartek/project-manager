package com.example.projectmanager.controllers;

import com.example.projectmanager.entities.Task;
import com.example.projectmanager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    @PostMapping("/project/{projectId}/task")
//    public void addNewTask(@PathVariable(value = "projectId") String projectId, @RequestBody Task task) {
//        this.taskService.addNewTask(projectId, task);
//    }
}
