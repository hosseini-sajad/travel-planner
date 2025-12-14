package com.xone.travelplanner.model;

import com.xone.travelplanner.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

@Entity
@Table(
        name = "place",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_place_title_city_country_latitude_longitude",
                        columnNames = {"title", "city", "country", "latitude", "longitude"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place extends BaseEntity {
    private String title;
    private String city;
    private String country;
    private double latitude;
    private double longitude;
    private String shortDescription;
    private Long rating;
    private Long reviewCount;
    private Boolean isFeatured;
}
