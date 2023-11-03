package com.example.projectmanager.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class DeveloperCredentials {
    @JsonProperty("userId")
    private final Long userId;
    @JsonProperty("specialization")
    private final Specialization specialization;
}
