package com.xone.travelplanner.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceImageDto {

    @Nullable
    private String imageUrl;

    @Size(max = 5_000_000, message = "Base64 content is too large")
    private String base64Content;

    @Size(max = 255, message = "File name must be at most 255 characters")
    private String fileName;

    @Size(max = 255, message = "Content type must be at most 255 characters")
    private String contentType;

    @Size(max = 255, message = "Alt text must be at most 255 characters")
    private String altText;

    @Min(value = 0, message = "Position must be zero or a positive number")
    private Integer position;
}