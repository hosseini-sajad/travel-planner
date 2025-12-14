package com.xone.travelplanner.core;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Error {
    EMAIL_IS_NOT_VALID(1, "Email is not valid"),
    EMAIL_IS_ALREADY_IN_USE(2, "Email is already in use!"),
    USER_NOT_FOUND(3, "User not found"),
    PASSWORD_IS_NOT_VALID(4, "Password is not valid"),
    PLACE_ALREADY_EXISTS(5, "Place already exists!"),

    ;

    final Integer code;
    final String message;
}
