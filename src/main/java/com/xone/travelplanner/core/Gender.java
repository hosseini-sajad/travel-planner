package com.xone.travelplanner.core;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    MALE(0, "Male"),
    FEMALE(1, "Female"),
    UNKNOWN(2, "Unknown");

    private final Integer key;
    private final String value;
}