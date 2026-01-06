package com.xone.travelplanner.model;

import com.xone.travelplanner.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @Builder.Default
    @OneToMany(
            mappedBy = "place",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("position ASC")
    private List<PlaceImage> images = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "created_by_user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_place_created_by_user")
    )
    private User createdBy;
}
