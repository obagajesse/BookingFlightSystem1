package com.obagajesse.BookingFlightSystem1.Controller;

import com.obagajesse.BookingFlightSystem1.DTO.Ticket;
import com.obagajesse.BookingFlightSystem1.ExceptionHandling.BookingNotFoundException;
import com.obagajesse.BookingFlightSystem1.ExceptionHandling.InvalidInputException;
import com.obagajesse.BookingFlightSystem1.Service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @Operation(summary = "Get a list of all available tickets.")
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets(){
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Get each ticket by its Id.")
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id){
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            throw new BookingNotFoundException("Ticket not found.");
        }
        return ResponseEntity.ok(ticket);
    }

    @Operation(summary = "Generate a ticket after payment has been successful.")
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        return new ResponseEntity<>(ticketService.createTicket(ticket), HttpStatus.CREATED);
    }

    @Operation(summary = "Cancel an existing ticket.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
        return new ResponseEntity<>("Ticket Deleted Succesfully.", HttpStatus.OK);
    }
}
