package com.example.projectmanager.utils;

import com.example.projectmanager.entities.Developer;
import com.example.projectmanager.entities.Task;

public abstract class Validation {
    public static Boolean isValidState(String state) {
        for (TaskState taskState : TaskState.values()) {
            if (taskState.name().equals(state)) return true;
        }
        return false;
    }

    public static Boolean isValidSpecialization(String specialization) {
        for (Specialization spec : Specialization.values()) {
            if (spec.name().equals(specialization)) return true;
        }
        return false;
    }

    public static Boolean isMatchingSpecialization(Task task, Developer dev) {
        return dev.getSpecialization().equals(task.getSpecialization());
    }
}
