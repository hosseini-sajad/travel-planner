package com.xone.travelplanner.service;

import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.model.Place;
import com.xone.travelplanner.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaceService {
    Place addPlace(Place place, User user) throws TravelException;

    List<Place> getAllPlaces();
}
