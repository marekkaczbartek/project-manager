package com.example.projectmanager.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public record ProjectCredentials(@JsonProperty("name") String name,
                                 @JsonProperty("users") List<DeveloperCredentials> users) {
}
