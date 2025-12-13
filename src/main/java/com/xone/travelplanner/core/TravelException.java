package com.xone.travelplanner.core;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TravelException extends Exception {
    Integer code;
    String message;

    public TravelException(Error error) {
        super(error.message + "::" + error.code);
        this.code = error.code;
        this.message = error.message;
    }
}
