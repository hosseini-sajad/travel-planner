package com.xone.travelplanner.core;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Error {
    ERROR01("0001", "Username is not valid"),
    ERROR02("0002", "Username is not active"),
    ERROR03("0003", "Please enter a valid username"),
    ERROR04("0004", "Please enter a valid password"),
    ERROR05("0005", "Password must be more than 6 characters long"),

    ;

    final String code;
    final String message;
}
