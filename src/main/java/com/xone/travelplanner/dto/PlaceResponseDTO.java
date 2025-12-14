package com.xone.travelplanner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xone.travelplanner.model.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceResponseDTO {
    private Place place;
    private List<Place> places;
    private Integer code;
    private String message;

    public static PlaceResponseDTO success(String message) {
        return PlaceResponseDTO.builder()
                .message(message)
                .build();
    }

    public static PlaceResponseDTO successWithPlaces(List<Place> places, String message) {
        return PlaceResponseDTO.builder()
                .places(places)
                .message(message)
                .build();
    }

    public static PlaceResponseDTO error(Integer code, String errorMessage) {
        return PlaceResponseDTO.builder()
                .code(code)
                .message(errorMessage)
                .build();
    }
}
