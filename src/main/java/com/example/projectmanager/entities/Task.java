package com.example.projectmanager.entities;


import com.example.projectmanager.utils.Specialization;
import com.example.projectmanager.utils.TaskState;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    private String id;
    private String projectId;
    private String createdById;
    private String assignedToId;

    private String name;
    private Date deadline;
    private TaskState taskState;
    private Specialization specialization;
    //TODO add estimation


    public Task(String projectId,
                String createdById,
                String assignedToId,
                String name,
                Date deadline,
                TaskState taskState,
                Specialization specialization) {
        this.projectId = projectId;
        this.createdById = createdById;
        this.assignedToId = assignedToId;
        this.name = name;
        this.deadline = deadline;
        this.taskState = taskState;
        this.specialization = specialization;
    }
}
