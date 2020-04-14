package com.space.exception;

public class InvalidShipIdException extends ShipException {

    public InvalidShipIdException() {
    }

    public InvalidShipIdException(String message) {
        super(message);
    }
}
