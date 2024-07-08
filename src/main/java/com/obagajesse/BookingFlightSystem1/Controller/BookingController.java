package com.obagajesse.BookingFlightSystem1.Controller;

import com.obagajesse.BookingFlightSystem1.DTO.Booking;
import com.obagajesse.BookingFlightSystem1.ExceptionHandling.BookingNotFoundException;
import com.obagajesse.BookingFlightSystem1.Service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.Api;


import java.util.List;

@RestController
@RequestMapping("/api/bookings")
//@Api(value = "Booking Management System", description = "Operations pertaining to bookings in the Flight Management System")
public class BookingController {

    private  BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @Operation(summary = "Make Booking.")
    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking){
        return new ResponseEntity<>(bookingService.createBooking(booking), HttpStatus.CREATED);
    }

    @Operation(summary = "Get Booking By Id")
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id){
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            throw new BookingNotFoundException("Booking not found.");
        }
        return ResponseEntity.ok(booking);
    }

    @Operation(summary = "Get all the available bookings.")
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings(){
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Delete Existing Bookings.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking Deleted!");
    }
}
