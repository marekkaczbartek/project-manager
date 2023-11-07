package com.example.projectmanager.utils;


import com.fasterxml.jackson.annotation.JsonProperty;

public record TaskStateDTO(@JsonProperty("state") String taskState) {
}
