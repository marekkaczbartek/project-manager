package com.example.projectmanager.utils;


import com.fasterxml.jackson.annotation.JsonProperty;

public record TaskStateDetails(@JsonProperty("state") String taskState) {
}
