package com.worldishes.services;

import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class S3Service {

    private final S3Client s3Client;

    public S3Service(S3Client s3Client) { this.s3Client = s3Client; }

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${amazon.aws.accessKeyId}")
    private String accessKeyId;

    @Value("${amazon.aws.secretKey}")
    private String secretAccessKey;

    @Value("${amazon.aws.region}")
    private String region;

    public String generatePreSignedUrl(String objectKey) {
        S3Presigner presigner = S3Presigner.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .build();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(builder -> builder
                .putObjectRequest(putObjectRequest)
                .signatureDuration(Duration.ofMinutes(10))
        );

        return presignedRequest.url().toString();
    }
}