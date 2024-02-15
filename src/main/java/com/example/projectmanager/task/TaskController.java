package com.example.projectmanager.task;

import com.example.projectmanager.utils.DateRange;
import com.example.projectmanager.utils.TaskDTO;
import com.example.projectmanager.utils.TaskStateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/project/{projectId}/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> addNewTask(@PathVariable(value = "projectId") Long projectId,
                                     @RequestBody TaskDTO taskDTO) {

        try {
            Task task = this.taskService.addNewTask(projectId, taskDTO);
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping("/{taskId}/assign/{devId}")
    public ResponseEntity<Task> assignTaskToDeveloper(@PathVariable(value = "projectId") Long projectId,
                                                      @PathVariable(value = "taskId") Long taskId,
                                                      @PathVariable(value = "devId") Long developerId) {
        try {
            Task task = this.taskService.assignTaskToDeveloper(projectId, taskId, developerId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        catch(RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping("/{taskId}/state")
    public ResponseEntity<Task> changeTaskState(@PathVariable(value = "projectId") Long projectId,
                                                @PathVariable(value = "taskId") Long taskId,
                                                @RequestBody TaskStateDTO taskStateDTO) {
        try {
            String taskState = taskStateDTO.taskState();
            Task task = this.taskService.changeTaskState(projectId, taskId, taskState);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping("/{taskId}/date")
    public ResponseEntity<Task> changeTaskDateRange(@PathVariable(value = "projectId") Long projectId,
                                                @PathVariable(value = "taskId") Long taskId,
                                                @RequestBody DateRange dateRange) {
        try {
            Task task = this.taskService.changeTaskDateRange(projectId, taskId, dateRange);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
