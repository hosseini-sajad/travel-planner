package com.xone.travelplanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "minio")
public record MinioProperties(
        @Value("${spring.minio.url}")
        String url,
        @Value("${spring.app.minio.accessKey}")
        String accessKey,
        @Value("${spring.app.minio.secretKey}")
        String secretKey,
        String bucket,
        @Value("${spring.app.minio.accessKey}")
        String region,
        @Value("${spring.app.minio.publicUrl}")
        String publicUrl
) {
}
