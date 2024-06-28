package com.obagajesse.BookingFlightSystem1.ExceptionHandling;

public class SeatNotFoundException extends RuntimeException {

    public SeatNotFoundException(String message){
        super(message);
    }
}
