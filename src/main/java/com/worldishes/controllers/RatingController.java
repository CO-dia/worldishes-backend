package com.worldishes.controllers;

import com.worldishes.models.Rating;
import com.worldishes.services.RatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<Rating> getRatings() {
        return ratingService.getRatings();
    }

    @GetMapping("/{ratingId}")
    public Rating getRating(@PathVariable("ratingId") UUID id) {
        return ratingService.getRating(id);
    }

    @PostMapping
    public Rating createRating(Rating rating) {
        return ratingService.createRating(rating);
    }
}
