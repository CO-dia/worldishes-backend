package com.worldishes.repositories;

import com.worldishes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository
        extends JpaRepository<User, UUID> {

    // Fetch user by their Google ID
    Optional<User> findByGoogleId(String googleId);

    // Fetch user by email (for any future reference if needed)
    Optional<User> findByEmail(String email);
}
