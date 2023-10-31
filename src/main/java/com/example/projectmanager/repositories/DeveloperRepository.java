package com.example.projectmanager.repositories;

import com.example.projectmanager.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, String> {
}
