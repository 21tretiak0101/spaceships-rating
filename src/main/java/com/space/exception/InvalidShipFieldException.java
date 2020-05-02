package com.space.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;

import java.util.stream.Collectors;

public class InvalidShipFieldException extends ShipException {

    public InvalidShipFieldException(String message) {
        super(message);
    }

    public InvalidShipFieldException(Errors errors) {
        super(message(errors));
    }

    public static String message(Errors errorsResolver) {
        return errorsResolver
                .getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList())
                .toString();
    }
}
