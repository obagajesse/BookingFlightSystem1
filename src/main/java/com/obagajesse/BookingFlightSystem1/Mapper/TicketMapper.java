package com.obagajesse.BookingFlightSystem1.Mapper;

import com.obagajesse.BookingFlightSystem1.DTO.Ticket;
import com.obagajesse.BookingFlightSystem1.Entity.TicketEntity;

public class TicketMapper {

    public static TicketEntity mapToTicketEntity(Ticket ticket){
        return new TicketEntity(
                ticket.getId(),
                ticket.getBookingId(),
                ticket.getPassengerId(),
                ticket.getTicketNumber(),
                ticket.getFlightId(),
                ticket.getSeatNumber(),
                ticket.getClassType(),
                ticket.getIssueDate(),
                ticket.getStatus(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }

    public static Ticket mapToTicket(TicketEntity ticketEntity){
        return new Ticket(
                ticketEntity.getId(),
                ticketEntity.getBookingId(),
                ticketEntity.getPassengerId(),
                ticketEntity.getTicketNumber(),
                ticketEntity.getFlightId(),
                ticketEntity.getSeatNumber(),
                ticketEntity.getClassType(),
                ticketEntity.getIssueDate(),
                ticketEntity.getStatus(),
                ticketEntity.getCreatedAt(),
                ticketEntity.getUpdatedAt()
        );
    }
}
