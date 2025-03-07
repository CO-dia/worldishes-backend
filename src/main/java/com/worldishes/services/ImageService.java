package com.worldishes.services;

import com.worldishes.models.Image;
import com.worldishes.repositories.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Optional<Image> getImageById(UUID id) {
        return imageRepository.findById(id);
    }
}
