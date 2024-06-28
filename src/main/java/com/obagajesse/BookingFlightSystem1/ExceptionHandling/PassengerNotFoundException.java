package com.obagajesse.BookingFlightSystem1.ExceptionHandling;

public class PassengerNotFoundException extends RuntimeException{

    public PassengerNotFoundException(String message){
        super(message);
    }
}
