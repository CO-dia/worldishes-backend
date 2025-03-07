package com.worldishes.controllers;

import com.worldishes.models.Image;
import com.worldishes.services.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // Endpoint to fetch an image by ID
    @GetMapping("/{id}")
    public Optional<Image> getImageById(@PathVariable UUID id) {
        return imageService.getImageById(id);
    }
}
