package com.worldishes.services;

import com.worldishes.models.User;
import com.worldishes.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Fetch user by Google ID
    public Optional<User> getUserByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId);
    }

    // Fetch user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Create or update user information (typically when a user logs in for the first time)
    public User createUser(User user) {
        // Check if the user already exists
        Optional<User> existingUser = getUserByGoogleId(user.getGoogleId());

        if (existingUser.isPresent()) {
            return existingUser.get(); // Return existing user if found
        } else {
            // Create a new user
            User newUser = new User(user.getEmail(), user.getName(), user.getProfileImageUrl(),
                    user.getGoogleId());
            return userRepository.save(newUser);
        }
    }
}

