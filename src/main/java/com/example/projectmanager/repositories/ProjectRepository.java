package com.example.projectmanager.repositories;

import com.example.projectmanager.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
