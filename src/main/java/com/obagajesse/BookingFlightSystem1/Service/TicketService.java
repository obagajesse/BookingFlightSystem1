package com.obagajesse.BookingFlightSystem1.Service;

import com.obagajesse.BookingFlightSystem1.DTO.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TicketService {

    Ticket createTicket(Ticket ticket);

    List<Ticket> getAllTickets();

    Ticket getTicketById(Long id);

    Ticket updateTicket(Ticket ticket);

    void deleteTicket(Long id);
}
