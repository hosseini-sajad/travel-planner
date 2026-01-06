package com.xone.travelplanner.service.impl;

import com.xone.travelplanner.config.MinioProperties;
import com.xone.travelplanner.core.Error;
import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.service.StorageService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioStorageServiceImp implements StorageService {

    private final MinioClient minioClient;
    private final MinioProperties properties;
    private static final String PLACES_BUCKET = "places";

    @Override
    public String uploadPlaceImage(UUID placeId, MultipartFile file) throws TravelException {
        String objectName = buildObjectName(file.getOriginalFilename());

        try (InputStream inputStream = file.getInputStream()) {
            createBucketIfNotExists();

            putObject(objectName, inputStream, file.getSize(), file.getContentType());
        } catch (Exception e) {
            throw new TravelException(Error.IMAGE_UPLOAD_FAILED);
        }

        return buildObjectUrl(objectName);
    }

    @Override
    public String uploadPlaceImage(UUID placeId, String base64Content, String fileName, String contentType)
            throws TravelException {
        if (base64Content == null || base64Content.isBlank()) {
            throw new TravelException(Error.IMAGE_UPLOAD_FAILED);
        }

        String objectName = buildObjectName(fileName);
        byte[] decoded = Base64.getDecoder().decode(base64Content);

        try (InputStream inputStream = new ByteArrayInputStream(decoded)) {
            createBucketIfNotExists();
            putObject(objectName, inputStream, decoded.length, contentType);
        } catch (Exception e) {
            throw new TravelException(Error.IMAGE_UPLOAD_FAILED);
        }

        return buildObjectUrl(objectName);
    }

    private void createBucketIfNotExists() throws Exception {
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(PLACES_BUCKET)
                .build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(PLACES_BUCKET)
                    .build());
        }
    }

    private void putObject(String objectName, InputStream inputStream, long size, String contentType)
            throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(PLACES_BUCKET)
                        .object(objectName)
                        .stream(inputStream, size, -1)
                        .contentType(contentType)
                        .build()
        );
    }

    private String buildObjectName(String originalFilename) {
        String extension = StringUtils.getFilenameExtension(originalFilename);
        String safeExtension = (extension != null && !extension.isBlank()) ? "." + extension : "";
        return Instant.now().toEpochMilli() + "-" + UUID.randomUUID() + safeExtension;
    }

    private String buildObjectUrl(String objectName) {
        String baseUrl = properties.publicUrl();
        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = properties.url();
        }
        return String.format("%s/%s/%s", baseUrl, PLACES_BUCKET, objectName);
    }
}
