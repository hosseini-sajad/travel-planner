package com.xone.travelplanner.model;

import com.xone.travelplanner.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "checklistItem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChecklistItem extends BaseEntity {
    private String tripId;
    private String name;
    private boolean isChecked;
}
