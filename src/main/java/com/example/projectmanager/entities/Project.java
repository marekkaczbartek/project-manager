package com.example.projectmanager.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="PROJECTS")
@JsonIdentityInfo(scope = Project.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "project")
    private List<Developer> developers;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    public Project(String name) {
        this.name = name;
    }
}
