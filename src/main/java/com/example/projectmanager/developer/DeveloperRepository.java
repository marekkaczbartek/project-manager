package com.example.projectmanager.developer;

import com.example.projectmanager.developer.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    List<Developer> findAllByProjectId(Long projectId);
    Optional<Developer> findByIdAndProjectId(Long developerId, Long projectId);
}
