package com.example.projectmanager.developer;

import com.example.projectmanager.project.Project;
import com.example.projectmanager.task.Task;
import com.example.projectmanager.user.User;
import com.example.projectmanager.utils.Specialization;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="DEVELOPERS")
@JsonIdentityInfo(scope = Developer.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Project project;

    private Specialization specialization;
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "assignedTo")

    private List<Task> tasks;


    public Developer(User user, Project project, Specialization specialization) {
        this.user = user;
        this.project = project;
        this.specialization = specialization;
    }

    public Developer(User user, Specialization specialization) {
        this.user = user;
        this.specialization = specialization;
    }
}
