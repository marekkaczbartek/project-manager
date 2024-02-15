package com.example.projectmanager.project;

import com.example.projectmanager.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT d.project FROM Developer d WHERE d.user.id = :userId")
    List<Project> findAllByUserId(@Param("userId") Long userId);
}
