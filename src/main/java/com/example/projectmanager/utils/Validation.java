package com.example.projectmanager.utils;

import com.example.projectmanager.developer.Developer;
import com.example.projectmanager.task.Task;

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

    private static boolean isPerfectSquare(int n) {
        int s = (int) Math.sqrt(n);
        return s * s == n;
    }

    public static Boolean isValidEstimation(Integer estimation) {
        return (isPerfectSquare(5 * estimation * estimation + 4) ||
                isPerfectSquare(5 * estimation * estimation - 4));
    }

    public static Boolean isMatchingSpecialization(Task task, Developer dev) {
        return dev.getSpecialization().equals(task.getSpecialization());
    }
}
