package com.obagajesse.BookingFlightSystem1.ExceptionHandling;

public class BookingNotFoundException extends RuntimeException{

    public BookingNotFoundException(String message){
        super(message);
    }


}
