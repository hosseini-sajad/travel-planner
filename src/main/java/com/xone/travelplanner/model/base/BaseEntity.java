package com.xone.travelplanner.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.event.internal.EntityState;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    @JsonIgnore
    private Integer version;

    @Column(nullable = false)
    @JsonIgnore
    private EntityState entityState;
}
