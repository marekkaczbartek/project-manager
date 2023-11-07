package com.example.projectmanager.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record TaskCredentials(@JsonProperty("name") String name,
                              @JsonProperty("description") String description,
                              @JsonProperty("assignedToId") Long assignedToId,
                              @JsonProperty("specialization") String specialization,
                              @JsonProperty("estimation") Integer estimation) {
}
