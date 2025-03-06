package com.worldishes.services;

import com.worldishes.UserDTO;
import com.worldishes.models.Rating;
import com.worldishes.models.User;
import com.worldishes.repositories.RatingRepository;
import com.worldishes.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingService {
    private static final Logger log = LoggerFactory.getLogger(RatingService.class);
    private final RatingRepository RatingRepository;
    private final UserRepository userRepository;

    public RatingService(RatingRepository RatingRepository, UserRepository userRepository) {
        this.RatingRepository = RatingRepository;
        this.userRepository = userRepository;
    }

    public List<RatingResponse> getRatings() {
        return RatingRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public RatingResponse getRating(UUID id) {
        Rating rating = RatingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found"));
        return convertToResponse(rating);
    }

    public Optional<RatingResponse> getRatingByDishAndUser(UUID dishId, UUID userId) {
        Optional<Rating> rating = RatingRepository.findByDishIdAndUserId(dishId, userId);
        return rating.map(this::convertToResponse);
    }

    public RatingResponse createRating(RatingRequest ratingRequest) {
        // Find the user by ID
        User user = userRepository.findById(ratingRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Rating newRating = new Rating();
        newRating.setDishId(ratingRequest.dishId());
        newRating.setUser(user);
        newRating.setStars(ratingRequest.stars());
        newRating.setComment(ratingRequest.comment());

        // Save rating and return response DTO
        Rating savedRating = RatingRepository.save(newRating);
        return convertToResponse(savedRating);
    }

    private RatingService.RatingResponse convertToResponse(Rating rating) {
        return new RatingService.RatingResponse(
                rating.getId(),
                new UserDTO(
                        rating.getUser().getName(),
                        rating.getUser().getProfileImageUrl()
                ),
                rating.getDishId(),
                rating.getStars(),
                rating.getComment()
        );
    }

    public record RatingResponse(
            UUID id,
            UserDTO user,
            UUID dishId,
            Integer stars,
            String comment
    ) {
    }

    public record RatingRequest(
            UUID userId,
            UUID dishId,
            Integer stars,
            String comment
    ) {
    }
}
