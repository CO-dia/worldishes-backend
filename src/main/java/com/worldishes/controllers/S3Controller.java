package com.worldishes.controllers;

import com.worldishes.services.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/generate-presigned-url")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) { this.s3Service = s3Service; }

    /**
     * Generates a presigned PUT URL with specified access type.
     */
    @GetMapping()
    public ResponseEntity<Map<String, Object>> generateUrl(@RequestParam(name = "filename", required = false, defaultValue = "") String filename) {
        //filename = buildFilename(filename);
        String url = s3Service.generatePresignedUrl(filename);
        return ResponseEntity.ok(Map.of("url", url, "file", filename));
    }
}