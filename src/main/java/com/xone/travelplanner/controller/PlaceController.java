package com.xone.travelplanner.controller;

import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.dto.PlaceDto;
import com.xone.travelplanner.model.Place;
import com.xone.travelplanner.model.User;
import com.xone.travelplanner.service.PlaceService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Place> createPlace(
            @Valid @RequestBody PlaceDto placeDto,
            @AuthenticationPrincipal User currentUser
    ) throws TravelException {
        Place place = modelMapper.map(placeDto, Place.class);
        Place createdPlace = placeService.addPlace(place, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlace);
    }

    @GetMapping
    public ResponseEntity<List<Place>> getAllPlaces() {
        List<Place> places = placeService.getAllPlaces();
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaceById(@PathVariable Long id) {
        // TODO: Implement get trip by id logic
        return ResponseEntity.ok("Get trip by id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlace(@PathVariable Long id) {
        // TODO: Implement delete trip logic
        return ResponseEntity.ok("Delete trip with id: " + id);
    }
}
