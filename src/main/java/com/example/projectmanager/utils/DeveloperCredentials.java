package com.example.projectmanager.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeveloperCredentials {
    @JsonProperty("userId")
    private final String userId;
    @JsonProperty("specialization")
    private final Specialization specialization;
}
