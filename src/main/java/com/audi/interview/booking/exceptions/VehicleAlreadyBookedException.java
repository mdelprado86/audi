package com.audi.interview.booking.exceptions;

public class VehicleAlreadyBookedException extends RuntimeException {

    public VehicleAlreadyBookedException() {
        super();
    }

    public VehicleAlreadyBookedException(String message) {
        super(message);
    }
}
