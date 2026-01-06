package com.xone.travelplanner.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "Latitude is required")
    private double latitude;

    @NotNull(message = "Longitude is required")
    private double longitude;

    @NotBlank(message = "Short description is required")
    @Size(min = 6, message = "Short description must be at least 6 characters")
    private String shortDescription;

    @Builder.Default
    private Boolean isFeatured = false;

    @Valid
    private List<PlaceImageDto> images;
}
