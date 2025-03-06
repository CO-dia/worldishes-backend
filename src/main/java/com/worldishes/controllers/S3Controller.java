package com.worldishes.controllers;

import com.worldishes.services.S3Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/generate-presigned-url")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) { this.s3Service = s3Service; }

    @GetMapping
    public String generatePresignedUrl(@RequestParam String objectKey) {
        return s3Service.generatePreSignedUrl(objectKey);
    }
}