package com.obagajesse.BookingFlightSystem1.Controller;

import com.obagajesse.BookingFlightSystem1.DTO.Passenger;
import com.obagajesse.BookingFlightSystem1.ExceptionHandling.PassengerNotFoundException;
import com.obagajesse.BookingFlightSystem1.Service.PassengerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private PassengerService passengerService;

    public PassengerController(PassengerService passengerService){
        this.passengerService = passengerService;
    }

    @Operation(summary = "Get a list of all passengers.")
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers(){
        List<Passenger> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengers);
    }

    @Operation(summary = "Get individual passengers by Id.")
    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id){
        Passenger passenger = passengerService.getPassengerById(id);
        if (passenger == null) {
            throw new PassengerNotFoundException("Passenger not found.");
        }
        return ResponseEntity.ok(passenger);
    }

    @Operation(summary = "Create a passenger after payment has been made.")
    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger){
        return new ResponseEntity<>(passengerService.createPassenger(passenger), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an existing passenger.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable Long id){
        passengerService.deletePassenger(id);
        return new ResponseEntity<>("Passenger Deleted.",HttpStatus.OK);
    }
}
