package com.example.projectmanager.repositories;

import com.example.projectmanager.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    List<Developer> findAllByProjectId(Long projectId);
    Optional<Developer> findByIdAndProjectId(Long developerId, Long projectId);
}
