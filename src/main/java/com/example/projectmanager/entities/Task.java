package com.example.projectmanager.entities;


import com.example.projectmanager.utils.Specialization;
import com.example.projectmanager.utils.TaskState;
import jakarta.persistence.*;
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
@Table(name="TASKS")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private Long createdById;
    private Long assignedToId;

    private String name;
    private Date deadline;
    private TaskState taskState;
    private Specialization specialization;
    //TODO add estimation


    public Task(Long projectId,
                Long createdById,
                Long assignedToId,
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
