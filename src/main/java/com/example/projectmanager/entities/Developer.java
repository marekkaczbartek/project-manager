package com.example.projectmanager.entities;

import com.example.projectmanager.utils.Specialization;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name="DEVELOPERS")
public class Developer {

    @Id
    private String id;
    private String userId;
    private String projectId;
    private Specialization specialization;

    public Developer(String projectId, Specialization specialization) {
        this.projectId = projectId;
        this.specialization = specialization;
    }
}
