package com.example.projectmanager.controllers;

import com.example.projectmanager.entities.User;
import com.example.projectmanager.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping("/users")
    public void addNewUser(@RequestBody User user) {
        this.userService.addNewUser(user);
    }
}
