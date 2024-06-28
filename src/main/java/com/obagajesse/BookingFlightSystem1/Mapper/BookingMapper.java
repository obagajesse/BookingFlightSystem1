package com.obagajesse.BookingFlightSystem1.Mapper;

import com.obagajesse.BookingFlightSystem1.DTO.Booking;
import com.obagajesse.BookingFlightSystem1.Entity.BookingEntity;

public class BookingMapper {

    public static BookingEntity mapToBookingEntity(Booking booking) {
        return new BookingEntity(
                booking.getId(),
                booking.getUserId(),
                booking.getFlightId(),
                booking.getStatus(),
                booking.getClassType(),
                booking.getCreatedAt(),
                booking.getUpdatedAt()
        );
    }

    public static Booking mapToBooking(BookingEntity bookingEntity) {
        return new Booking(
                bookingEntity.getId(),
                bookingEntity.getUserId(),
                bookingEntity.getFlightId(),
                bookingEntity.getStatus(),
                bookingEntity.getClassType(),
                bookingEntity.getCreatedAt(),
                bookingEntity.getUpdatedAt()
        );
    }
}
