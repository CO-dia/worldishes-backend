package com.worldishes.controllers;

import com.worldishes.models.User;
import com.worldishes.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        RatingService.RatingResponse createdRating = ratingService.createRating(ratingRequest, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }
}
