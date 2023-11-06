package com.example.projectmanager.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

public record DeveloperCredentials(@JsonProperty("userId") Long userId,
                                   @JsonProperty("specialization") String specialization) {
}
