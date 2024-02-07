package com.example.projectmanager.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeveloperDTO(@JsonProperty("userId") Long userId,
                           @JsonProperty("specialization") String specialization) {
}
