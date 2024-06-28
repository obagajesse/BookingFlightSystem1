package com.obagajesse.BookingFlightSystem1.Controller;

import com.obagajesse.BookingFlightSystem1.DTO.Seat;
import com.obagajesse.BookingFlightSystem1.ExceptionHandling.InvalidInputException;
import com.obagajesse.BookingFlightSystem1.ExceptionHandling.SeatNotFoundException;
import com.obagajesse.BookingFlightSystem1.Service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private SeatService seatService;

    public SeatController(SeatService seatService){
        this.seatService = seatService;
    }

    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeats(){
        List<Seat> seats = seatService.getAllSeats();
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id){
        Seat seat = seatService.getSeatById(id);
        if (seat == null) {
            throw new SeatNotFoundException("Seat not found.");
        }
        return ResponseEntity.ok(seat);
    }

    @PostMapping
    public ResponseEntity<Seat> addSeat(@RequestBody Seat seat){
        if (seat == null ) {
            throw new InvalidInputException("Invalid Seat Input.");
        }
        return new ResponseEntity<>(seatService.createSeat(seat), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable Long id){
        seatService.deleteSeat(id);
        return new ResponseEntity<>("Seat Deleted",HttpStatus.OK);
    }
}
