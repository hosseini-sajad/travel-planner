package com.xone.travelplanner.core;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TravelException extends Exception {
    String code;
    String message;

    public TravelException(Error error) {
        super(error.code + "::" + error.message);
        this.code = error.code;
        this.message = error.message;
    }
}
