package com.example.projectmanager.task;


import com.example.projectmanager.developer.Developer;
import com.example.projectmanager.project.Project;
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

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TASKS")
@JsonIdentityInfo(scope = Task.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Developer createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Developer assignedTo;


    private String name;
    private String description;
    private TaskState taskState;
    private Specialization specialization;
    private Integer estimation;
    private Date createdAt;
    private Date startDate;
    private Date endDate;
    //TODO deadline/date range


    public Task(Project project,
                Developer createdBy,
                Developer assignedTo,
                String name,
                String description,
                TaskState taskState,
                Specialization specialization,
                Integer estimation,
                Date createdAt,
                Date startDate,
                Date endDate) {
        this.project = project;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.name = name;
        this.description = description;
        this.taskState = taskState;
        this.specialization = specialization;
        this.estimation = estimation;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
