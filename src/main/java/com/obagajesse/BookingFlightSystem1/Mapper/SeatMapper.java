package com.obagajesse.BookingFlightSystem1.Mapper;

import com.obagajesse.BookingFlightSystem1.DTO.Seat;
import com.obagajesse.BookingFlightSystem1.Entity.SeatEntity;

public class SeatMapper {

    public static SeatEntity mapToSeatEntity(Seat seat){
        return new SeatEntity(
                seat.getId(),
                seat.getFlightId(),
                seat.getSeatNumber(),
                seat.getAvailability(),
                seat.getCreatedAt(),
                seat.getUpdatedAt()
        );
    }

    public static Seat mapToSeat(SeatEntity seatEntity){
        return new Seat(
                seatEntity.getId(),
                seatEntity.getFlightId(),
                seatEntity.getSeatNumber(),
                seatEntity.getAvailability(),
                seatEntity.getCreatedAt(),
                seatEntity.getUpdatedAt()
        );
    }
}
