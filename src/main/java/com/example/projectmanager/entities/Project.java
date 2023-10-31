package com.example.projectmanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {

    @Id
    private String id;
    private String userId;
    private ProjectCredentials projectCredentials;

}
