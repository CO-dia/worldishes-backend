package com.worldishes.services;

import com.worldishes.models.Rating;
import com.worldishes.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RatingService {
    private final RatingRepository RatingRepository;

    public RatingService(RatingRepository RatingRepository) {
        this.RatingRepository = RatingRepository;
    }

    public List<Rating> getRatings() {
        return RatingRepository.findAll();
    }

    public Rating getRating(UUID id) {
        return RatingRepository.findById(id).orElse(null);
    }

    public Optional<Rating> getRatingByDishAndUser(UUID dishId, UUID userId) {
        return RatingRepository.findByDishIdAndUserId(dishId, userId);
    }

    public Rating createRating(Rating rating) {
        return RatingRepository.save(rating);
    }
}
