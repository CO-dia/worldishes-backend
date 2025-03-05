package com.worldishes.repositories;

import com.worldishes.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository
        extends JpaRepository<Rating, UUID> {

    Optional<Rating> findByDishIdAndUserId(UUID dishId, UUID userId);
}
