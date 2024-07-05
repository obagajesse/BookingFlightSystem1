package com.obagajesse.BookingFlightSystem1.Controller;

import com.obagajesse.BookingFlightSystem1.DTO.Seat;
import com.obagajesse.BookingFlightSystem1.ExceptionHandling.SeatNotFoundException;
import com.obagajesse.BookingFlightSystem1.Service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get a list of all available seats.")
    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeats(){
        List<Seat> seats = List.of(seatService.getAllSeats());
        return ResponseEntity.ok(seats);
    }

    @Operation(summary = "Get each available seat by Id.")
    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id){
        Seat seat = seatService.getSeatById(id);
        if (seat == null) {
            throw new SeatNotFoundException("Seat not found.");
        }
        return ResponseEntity.ok(seat);
    }

    @Operation(summary = "Add an available seat.")
    @PostMapping
    public ResponseEntity<Seat> addSeat(@RequestBody Seat seat){
        return new ResponseEntity<>(seatService.createSeat(seat), HttpStatus.CREATED);
    }

    @Operation(summary = "Remove an available seat.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable Long id){
        seatService.deleteSeat(id);
        return new ResponseEntity<>("Seat Deleted",HttpStatus.OK);
    }
}
