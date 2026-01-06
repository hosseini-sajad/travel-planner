package com.xone.travelplanner.config;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfig {

    @Bean
    public MinioClient minioClient(MinioProperties minioProperties) throws MinioException {
        MinioClient.Builder builder = MinioClient.builder()
                .endpoint(minioProperties.url())
                .credentials(minioProperties.accessKey(), minioProperties.secretKey());

        if (minioProperties.region() != null && !minioProperties.region().isBlank()) {
            builder.region(minioProperties.region());
        }

        try {
            return builder.build();
        } catch (Exception e) {
            throw new MinioException("Error initializing Minio client");
        }
    }
}
