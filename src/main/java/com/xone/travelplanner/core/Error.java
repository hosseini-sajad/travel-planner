package com.xone.travelplanner.core;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum Error {
    EMAIL_IS_NOT_VALID(1, "Email is not valid", HttpStatus.UNAUTHORIZED),
    EMAIL_IS_ALREADY_IN_USE(2, "Email is already in use!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(3, "User not found", HttpStatus.BAD_REQUEST),
    PASSWORD_IS_NOT_VALID(4, "Password is not valid", HttpStatus.UNAUTHORIZED),
    PLACE_ALREADY_EXISTS(5, "Place already exists!", HttpStatus.BAD_REQUEST),
    PLACE_CREATION_RESTRICTED(6, "Only admins can create places", HttpStatus.FORBIDDEN),
    PLACE_NOT_FOUND(7, "Place not found", HttpStatus.NOT_FOUND),
    IMAGE_UPLOAD_FAILED(8, "Unable to upload image", HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_UPLOAD_FAILED_INTO_MINIO(9, "Unable to upload image into minio", HttpStatus.INTERNAL_SERVER_ERROR)

    ;

    final Integer code;
    final String message;
    final HttpStatus status;
}
