package com.example.projectmanager.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TaskDetails(@JsonProperty("name") String name,
                          @JsonProperty("description") String description,
                          @JsonProperty("createdBy") Long createdById,
                          @JsonProperty("assignedToId") Long assignedToId,
                          @JsonProperty("specialization") String specialization,
                          @JsonProperty("estimation") Integer estimation,
                          @JsonProperty("startDate") DateRange dateRange) {
}
