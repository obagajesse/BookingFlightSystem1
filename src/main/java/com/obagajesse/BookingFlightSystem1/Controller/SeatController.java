package com.obagajesse.BookingFlightSystem1.Controller;

import com.obagajesse.BookingFlightSystem1.DTO.Seat;
import com.obagajesse.BookingFlightSystem1.Service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

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
        if(seat == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(seat);
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<Seat>> getSeatsByFlightId(@PathVariable Long flightId){
        List<Seat> seats = seatService.getSeatsByFlightId(flightId);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/availability/{flightId}/{seatnumber}")
    public ResponseEntity<String> checkSeatAvailability(@PathVariable Long flightId,@PathVariable String seatNumber){
        boolean isAvailable = seatService.checkSeatAvailability(flightId,seatNumber);
        if(isAvailable){
            return ResponseEntity.ok("Seat Is Available");
        }else{
            return ResponseEntity.ok("Seat Not Available");
        }
    }

    @PostMapping
    public ResponseEntity<Seat> addSeat(@RequestBody Seat seat){
        Seat createdSeat = seatService.createSeat(seat);
        return new ResponseEntity<>(createdSeat, HttpStatus.CREATED);
    }

    @PostMapping("/book/{flightId}/{seatNumber}")
    public ResponseEntity<String> bookSeat(@PathVariable Long flightId, @PathVariable String seatNumber){
        try{
            Seat bookedSeat = seatService.bookSeat(flightId,seatNumber);
            return ResponseEntity.ok("Seat Booked Successfully:" + bookedSeat.getSeatNumber());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed To Book Seat:" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable Long id,@RequestBody Seat seat){
        Seat existingSeat = seatService.getSeatById(id);
        if(existingSeat == null){
            return ResponseEntity.notFound().build();
        }

        seat.setId(existingSeat.getId());
        Seat updatedSeat = seatService.updateSeat(seat);
        return ResponseEntity.ok(updatedSeat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable Long id){
        Seat existingSeat = seatService.getSeatById(id);
        if(existingSeat == null){
            return ResponseEntity.notFound().build();
        }

        seatService.deleteSeat(id);
        return ResponseEntity.ok("Seat Deleted Successfully");
    }
}
