package com.xone.travelplanner.service;

import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.dto.PlaceDto;
import com.xone.travelplanner.model.Place;
import com.xone.travelplanner.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public interface PlaceService {
    Place addPlace(PlaceDto placeDto, User currentUser) throws TravelException;

    List<Place> getAllPlaces();

    Place getPlaceById(UUID id) throws TravelException;

    Place addImage(UUID placeId, String altText, Integer position, MultipartFile file, User user) throws TravelException;
}
