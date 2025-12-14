package com.xone.travelplanner.service;

import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.model.Place;
import org.springframework.stereotype.Service;

@Service
public interface PlaceService {
    void addPlace(Place place) throws TravelException;
}
