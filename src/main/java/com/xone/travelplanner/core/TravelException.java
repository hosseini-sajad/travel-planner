package com.xone.travelplanner.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class TravelException extends Exception {
    private final Error error;
    private final HttpStatus status;
    Integer code;
    String message;

    public TravelException(Error error) {
        super(error.message + "::" + error.code);
        this.error = error;
        this.code = error.code;
        this.message = error.message;
        this.status = error.status;
    }
}
