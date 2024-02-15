package com.example.projectmanager.user;

import com.example.projectmanager.project.Project;
import com.example.projectmanager.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        return new ResponseEntity<>(this.userService.addNewUser(user), HttpStatus.CREATED);
    }

    @GetMapping("users/{userId}/projects")
    public List<Project> getUserProjects(@PathVariable(value = "userId") Long userId) {
        try {
            return this.userService.getUserProjects(userId);
        }
        catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
