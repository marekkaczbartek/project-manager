package com.example.projectmanager.repositories;

import com.example.projectmanager.entities.Task;
import com.example.projectmanager.utils.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProjectId(Long projectId);
    List<Task> findAllByProjectIdAndSpecialization(Long projectId, Specialization specialization);
    Optional<Task> findByIdAndProjectId(Long taskId, Long projectId);

}
