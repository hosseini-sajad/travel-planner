package com.xone.travelplanner.controller;

import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.dto.PlaceDto;
import com.xone.travelplanner.model.Place;
import com.xone.travelplanner.model.User;
import com.xone.travelplanner.service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping
    public ResponseEntity<Place> createPlace(
            @Valid @RequestBody PlaceDto placeDto,
            @AuthenticationPrincipal User currentUser
    ) throws TravelException {
        Place createdPlace = placeService.addPlace(placeDto, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlace);
    }

    @GetMapping
    public ResponseEntity<List<Place>> getAllPlaces() {
        List<Place> places = placeService.getAllPlaces();
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable UUID id) throws TravelException {
        Place place = placeService.getPlaceById(id);
        return ResponseEntity.ok(place);
    }

    @PostMapping(path = "/{id}/images", consumes = "multipart/form-data")
    public ResponseEntity<Place> uploadPlaceImage(
            @PathVariable UUID id,
            @RequestParam(value = "altText", required = false) String altText,
            @RequestParam(value = "position", required = false) Integer position,
            @RequestPart("file") MultipartFile file,
            @AuthenticationPrincipal User currentUser
    ) throws TravelException {
        Place place = placeService.addImage(id, altText, position, file, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(place);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlace(@PathVariable Long id) {
        // TODO: Implement delete trip logic
        return ResponseEntity.ok("Delete trip with id: " + id);
    }
}
