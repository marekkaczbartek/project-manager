package com.example.projectmanager.utils;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.entities.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectCredentials {
    @JsonProperty("name")
    private String name;
    @JsonProperty("users")
    private HashMap<Long, Specialization> users;
}
