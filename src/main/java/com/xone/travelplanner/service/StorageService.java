package com.xone.travelplanner.service;

import com.xone.travelplanner.core.TravelException;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface StorageService {

    String uploadPlaceImage(UUID placeId, MultipartFile file) throws TravelException;

    String uploadPlaceImage(UUID placeId, String base64Content, String fileName, String contentType)
            throws TravelException;
}
