package com.xone.travelplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xone.travelplanner.core.Gender;
import com.xone.travelplanner.core.UserRole;
import com.xone.travelplanner.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private Boolean isActive;
    private Gender gender;
    private String phone;
    private Boolean isFirstLogin;
    private UserRole role;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date lastLogin;
}
