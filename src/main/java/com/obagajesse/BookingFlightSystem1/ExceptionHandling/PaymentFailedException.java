package com.obagajesse.BookingFlightSystem1.ExceptionHandling;

public class PaymentFailedException extends RuntimeException{

    public PaymentFailedException(String message){
        super(message);
    }
}
