package com.worldishes.controllers;

import com.worldishes.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<RatingService.RatingResponse> getRatings() {
        return ratingService.getRatings();
    }

    @GetMapping("/{id}")
    public RatingService.RatingResponse getRating(@PathVariable String id) {
        return ratingService.getRating(id);
    }

    @PostMapping
    public ResponseEntity<RatingService.RatingResponse> createRating(@RequestBody RatingService.RatingRequest ratingRequest) {
        RatingService.RatingResponse createdRating = ratingService.createRating(ratingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }
}
