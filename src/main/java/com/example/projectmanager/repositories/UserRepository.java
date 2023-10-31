package com.example.projectmanager.repositories;

import com.example.projectmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
