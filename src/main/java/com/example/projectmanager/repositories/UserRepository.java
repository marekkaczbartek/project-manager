package com.example.projectmanager.repositories;

import com.example.projectmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT d.user FROM Developer d WHERE d.project.id = :projectId")
    List<User> findAllByProjectId(@Param("projectId") Long projectId);
}
