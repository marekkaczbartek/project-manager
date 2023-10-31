package com.example.projectmanager.entities;

import com.example.projectmanager.utils.Specialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="DEVELOPERS")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long projectId;
    private Specialization specialization;

    public Developer(Long userId, Long projectId, Specialization specialization) {
        this.userId = userId;
        this.projectId = projectId;
        this.specialization = specialization;
    }
}
