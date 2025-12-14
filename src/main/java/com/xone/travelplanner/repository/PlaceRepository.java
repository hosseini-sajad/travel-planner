package com.xone.travelplanner.repository;

import com.xone.travelplanner.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID> {

    boolean existsByTitleAndCityAndCountryAndLatitudeAndLongitude(
            String title,
            String city,
            String country,
            double latitude,
            double longitude);
}
