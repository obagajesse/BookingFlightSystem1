package com.obagajesse.BookingFlightSystem1.DTO;

import com.obagajesse.BookingFlightSystem1.Enum.ClassType;
import com.obagajesse.BookingFlightSystem1.Enum.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private Long id;
    private Long passengerId;
    private Long bookingId;
    private String ticketNumber;
    private byte[] qrCode;
    private Long flightId;
    private String seatNumber;
    private ClassType classType;
    private LocalDateTime issueDate;
    private TicketStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Ticket(Long id, Long bookingId, Long passengerId, String ticketNumber, byte[] qrCode, Long flightId, String seatNumber, ClassType classType, LocalDateTime issueDate, TicketStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.ticketNumber = ticketNumber;
        this.qrCode = new byte[0];
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.issueDate = issueDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
