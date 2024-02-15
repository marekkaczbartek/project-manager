package com.example.projectmanager.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProjectDetails(@JsonProperty("name") String name,
                             @JsonProperty("users") List<DeveloperDetails> users) {
}
