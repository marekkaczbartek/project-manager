package com.example.projectmanager.services;

import com.example.projectmanager.entities.User;
import com.example.projectmanager.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(User user) {
        this.userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
