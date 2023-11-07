package com.example.projectmanager.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name="ASSIGNMENTS")
@JsonIdentityInfo(scope = Assignment.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Assignment {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "assignment")
    private List<TaskDevPair> assignments;
}
