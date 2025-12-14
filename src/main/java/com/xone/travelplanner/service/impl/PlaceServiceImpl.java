package com.xone.travelplanner.service.impl;

import com.xone.travelplanner.core.Error;
import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.model.Place;
import com.xone.travelplanner.repository.PlaceRepository;
import com.xone.travelplanner.service.PlaceService;
import org.hibernate.event.internal.EntityState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    @Transactional
    public void addPlace(Place place) throws TravelException {
        if (placeRepository.existsByTitleAndCityAndCountryAndLatitudeAndLongitude(
                place.getTitle(), place.getCity(), place.getCountry(), place.getLatitude(), place.getLongitude())) {
            throw new TravelException(Error.PLACE_ALREADY_EXISTS);
        }

        place.setRating(0L);
        place.setReviewCount(0L);
        place.setEntityState(EntityState.PERSISTENT);
        placeRepository.save(place);
    }
}
