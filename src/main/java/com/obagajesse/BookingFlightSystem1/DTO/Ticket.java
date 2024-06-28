package com.obagajesse.BookingFlightSystem1.DTO;

import com.obagajesse.BookingFlightSystem1.Enum.ClassType;
import com.obagajesse.BookingFlightSystem1.Enum.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private Long id;
    private Long passengerId;
    private Long bookingId;
    private String ticketNumber;
    private Long flightId;
    private String seatNumber;
    private ClassType classType;
    private LocalDateTime issueDate;
    private TicketStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
