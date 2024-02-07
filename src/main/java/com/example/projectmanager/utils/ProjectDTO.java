package com.example.projectmanager.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProjectDTO(@JsonProperty("name") String name,
                         @JsonProperty("users") List<DeveloperDTO> users) {
}
