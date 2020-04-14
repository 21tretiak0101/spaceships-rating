package com.space.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class InvalidShipFieldsException extends ShipException {

    public InvalidShipFieldsException() {
    }

    public InvalidShipFieldsException(String message) {
        super(message);
    }

    public static String message(BindingResult result) {
        return result
                .getAllErrors().stream()
                .map(
                        DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(
                        Collectors.toList())
                .toString();
    }
}
