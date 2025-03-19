package com.worldishes.services;

import com.worldishes.DTO.UserDTO;
import com.worldishes.models.Dish;
import com.worldishes.models.Rating;
import com.worldishes.models.User;
import com.worldishes.repositories.DishRepository;
import com.worldishes.repositories.RatingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final DishRepository dishRepository;

    public RatingService(RatingRepository ratingRepository, DishRepository dishRepository) {
        this.ratingRepository = ratingRepository;
        this.dishRepository = dishRepository;
    }

    public List<RatingResponse> getRatings() {
        return ratingRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public RatingResponse getRating(String id) {
        UUID ratingId = UUID.fromString(id);
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new RuntimeException("Rating not found"));
        return convertToResponse(rating);
    }

    public Optional<RatingResponse> getRatingByDishAndUser(UUID dishId, UUID userId) {
        Optional<Rating> rating = ratingRepository.findByDishIdAndUserId(dishId, userId);
        return rating.map(this::convertToResponse);
    }

    public RatingResponse createRating(@Validated RatingRequest ratingRequest, User user) {
        // Recalculate average rating for the dish
        Rating newRating = new Rating();
        newRating.setDishId(ratingRequest.dishId());
        newRating.setUser(user);
        newRating.setStars(ratingRequest.stars());
        newRating.setComment(ratingRequest.comment());

        // Save rating and return response DTO
        Rating savedRating = ratingRepository.save(newRating);

        UUID dishId = ratingRequest.dishId();
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
        Optional<List<Rating>> ratings = ratingRepository.findAllByDishId(dishId);
        if (ratings.isPresent()) {
            double averageRating = ratings.get().stream()
                    .filter(rating -> rating.getStars() != null) // filter ratings where stars are not null
                    .mapToInt(Rating::getStars)
                    .average()
                    .orElse(0);

            // Get the count of ratings with stars
            long ratingCount = ratings.get().stream()
                    .filter(rating -> rating.getStars() != null)
                    .count();

            // Update the dish with the new average rating
            dish.setRatingAverage(averageRating);
            dish.setRatingCount((int) ratingCount);
            dishRepository.save(dish);
        }

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
            @NonNull
            UUID userId,
            @NonNull
            UUID dishId,
            @NonNull
            Integer stars,
            String comment
    ) {
    }
}
