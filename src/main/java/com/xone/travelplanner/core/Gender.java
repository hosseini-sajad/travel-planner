package com.xone.travelplanner.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE(0, "Male"),
    FEMALE(1, "Female"),
    UNKNOWN(2, "Unknown");

    private final Integer Key;
    private final String value;
}