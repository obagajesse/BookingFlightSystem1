package com.obagajesse.BookingFlightSystem1.ExceptionHandling;

public class InvalidInputException extends RuntimeException{

    public InvalidInputException(String message){
        super(message);
    }
}
