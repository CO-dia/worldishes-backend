package com.worldishes.controllers;

import com.worldishes.models.User;
import com.worldishes.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to create or fetch a user by Google OAuth data
    @PostMapping
    public User createOrGetUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
