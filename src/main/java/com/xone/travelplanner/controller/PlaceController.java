package com.xone.travelplanner.controller;

import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.dto.PlaceDto;
import com.xone.travelplanner.dto.PlaceResponseDTO;
import com.xone.travelplanner.model.Place;
import com.xone.travelplanner.service.PlaceService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PlaceResponseDTO> createTrip(
            @Valid @RequestBody PlaceDto placeDto) {
        try {
            Place place = modelMapper.map(placeDto, Place.class);
            placeService.addPlace(place);
            return ResponseEntity.ok(
                    PlaceResponseDTO.success(
                            "Place created successfully"
                    )
            );
        } catch (TravelException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(PlaceResponseDTO.error(e.getCode(), e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<PlaceResponseDTO> getAllPlaces() {
        try {
            List<Place> places = placeService.getAllPlaces();
            PlaceResponseDTO response = PlaceResponseDTO.builder()
                    .places(places)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(PlaceResponseDTO.error(500, "Error retrieving places: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTripById(@PathVariable Long id) {
        // TODO: Implement get trip by id logic
        return ResponseEntity.ok("Get trip by id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        // TODO: Implement delete trip logic
        return ResponseEntity.ok("Delete trip with id: " + id);
    }
}
