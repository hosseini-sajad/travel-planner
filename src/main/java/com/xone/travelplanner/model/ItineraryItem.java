package com.xone.travelplanner.model;

import com.xone.travelplanner.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;


@Entity
@Table(name = "itineraryItem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItineraryItem extends BaseEntity {
    private String tripId;
    private Date date;
    private String title;
    private String description;
    private String location;
}
