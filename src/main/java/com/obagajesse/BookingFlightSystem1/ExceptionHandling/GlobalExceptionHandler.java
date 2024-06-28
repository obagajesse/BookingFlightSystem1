package com.obagajesse.BookingFlightSystem1.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleBookingNotFound(BookingNotFoundException ex){
        CustomErrorResponse errorResponse = new CustomErrorResponse(404,"Error 404 Booking Not Found.");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleSeatNotFound(SeatNotFoundException ex){
        CustomErrorResponse errorResponse = new CustomErrorResponse(404,"Error 404 Seat Not Found.");
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PassengerNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handlePassengerNotFound(PassengerNotFoundException ex){
        CustomErrorResponse errorResponse = new CustomErrorResponse(404,"Error 404 Passenger Not Found.");
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<CustomErrorResponse> handlePaymentFailed(PaymentFailedException ex){
        CustomErrorResponse errorResponse = new CustomErrorResponse(404,"Error 404 Payment Failed.");
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidInput(InvalidInputException ex){
        CustomErrorResponse errorResponse = new CustomErrorResponse(405,"Invalid Input");
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
