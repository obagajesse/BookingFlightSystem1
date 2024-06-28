package com.obagajesse.BookingFlightSystem1.DTO;

import com.obagajesse.BookingFlightSystem1.Enum.BookingStatus;
import com.obagajesse.BookingFlightSystem1.Enum.ClassType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    private Long id;
    private Long userId;
    private Long flightId;
    private BookingStatus status;
    private ClassType classType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Booking(Long id, Long userId, Long flightId, BookingStatus status, ClassType classType) {
        this.id = id;
        this.userId = userId;
        this.flightId = flightId;
        this.status = status;
        this.classType = classType;
    }
}
