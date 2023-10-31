package com.example.projectmanager.repositories;

import com.example.projectmanager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
