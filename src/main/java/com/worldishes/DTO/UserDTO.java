package com.worldishes.DTO;

// Record for returning user data (instead of full User entity)
public record UserDTO(
        String name,
        String profileImageUrl
) {
}
