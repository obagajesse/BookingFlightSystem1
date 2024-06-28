package com.obagajesse.BookingFlightSystem1.DTO;

import com.obagajesse.BookingFlightSystem1.Enum.ClassType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    private Long id;
    private Long flightId;
    private String seatNumber;
    private ClassType classType;
    private Boolean availability;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Seat(Long id, Long flightId, String seatNumber, Boolean availability, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.availability = availability;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
