package com.xone.travelplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xone.travelplanner.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceImage extends BaseEntity {

    private String imageUrl;
    private String altText;
    private Integer position;
    @Transient
    @JsonIgnore
    private String base64Content;
    @Transient
    @JsonIgnore
    private String fileName;
    @Transient
    @JsonIgnore
    private String contentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "place_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_place_image_place")
    )
    @JsonIgnore
    private Place place;
}
