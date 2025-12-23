package com.xone.travelplanner.service.impl;

import com.xone.travelplanner.core.Error;
import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.core.UserRole;
import com.xone.travelplanner.model.Place;
import com.xone.travelplanner.model.User;
import com.xone.travelplanner.repository.PlaceRepository;
import com.xone.travelplanner.service.PlaceService;
import org.hibernate.event.internal.EntityState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    @Transactional
    public Place addPlace(Place place, User user) throws TravelException {
        if (user == null || user.getRole() != UserRole.Admin) {
            throw new TravelException(Error.PLACE_CREATION_RESTRICTED);
        }

        if (placeRepository.existsByTitleAndCityAndCountryAndLatitudeAndLongitude(
                place.getTitle(), place.getCity(), place.getCountry(), place.getLatitude(), place.getLongitude())) {
            throw new TravelException(Error.PLACE_ALREADY_EXISTS);
        }

        place.setRating(0L);
        place.setReviewCount(0L);
        place.setCreatedBy(user);
        place.setEntityState(EntityState.PERSISTENT);
        return placeRepository.save(place);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public Place getPlaceById(UUID id) throws TravelException {
        return placeRepository.findById(id)
                .orElseThrow(() -> new TravelException(Error.PLACE_NOT_FOUND));
    }
}
