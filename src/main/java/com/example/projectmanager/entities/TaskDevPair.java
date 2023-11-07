package com.example.projectmanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDevPair {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public TaskDevPair(Assignment assignment, Developer developer, Task task) {
        this.assignment = assignment;
        this.developer = developer;
        this.task = task;
    }
}
