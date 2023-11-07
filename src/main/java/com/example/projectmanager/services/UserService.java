package com.example.projectmanager.services;

import com.example.projectmanager.entities.Project;
import com.example.projectmanager.entities.User;
import com.example.projectmanager.exceptions.UserNotFoundException;
import com.example.projectmanager.repositories.ProjectRepository;
import com.example.projectmanager.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public UserService(UserRepository userRepository,
                       ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public User addNewUser(User user) {
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<Project> getUserProjects(Long userId) {
        if (this.projectRepository.
                findById(userId).
                isPresent()) {
            return this.projectRepository.findAllByUserId(userId);
        }
        throw new UserNotFoundException("User not found");
    }
}
