package com.example.projectmanager.user;

import com.example.projectmanager.developer.Developer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="USERS")
@JsonIdentityInfo(scope = User.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "user")
    private List<Developer> developers;

    //TODO add more fields perhaps


    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
