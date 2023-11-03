package com.example.projectmanager.entities;


import com.example.projectmanager.utils.Specialization;
import com.example.projectmanager.utils.TaskState;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(scope = Task.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIdentityReference(alwaysAsId = true)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Developer assignedTo;

    private String name;
    private Date deadline;
    private TaskState taskState;
    private Specialization specialization;
    //TODO add estimation


    public Task(Project project,
                Developer assignedTo,
                String name,
                Date deadline,
                TaskState taskState,
                Specialization specialization) {
        this.project = project;
        this.assignedTo = assignedTo;
        this.name = name;
        this.deadline = deadline;
        this.taskState = taskState;
        this.specialization = specialization;
    }
}
