package com.obagajesse.BookingFlightSystem1.Mapper;

import com.obagajesse.BookingFlightSystem1.DTO.Passenger;
import com.obagajesse.BookingFlightSystem1.Entity.PassengerEntity;

public class PassengerMapper {

    public static PassengerEntity mapToPassengerEntity(Passenger passenger){
        return new PassengerEntity(
                passenger.getId(),
                passenger.getBookingId(),
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getDateOfBirth(),
                passenger.getPassportNumber(),
                passenger.getNationality(),
                passenger.getSeatNumber(),
                passenger.getClassType(),
                passenger.getCreatedAt(),
                passenger.getUpdatedAt()
        );
    }

    public static Passenger mapToPassenger(PassengerEntity passengerEntity){
        return new Passenger(
                passengerEntity.getId(),
                passengerEntity.getBookingId(),
                passengerEntity.getFirstName(),
                passengerEntity.getLastName(),
                passengerEntity.getDateOfBirth(),
                passengerEntity.getPassportNumber(),
                passengerEntity.getNationality(),
                passengerEntity.getSeatNumber(),
                passengerEntity.getClassType(),
                passengerEntity.getCreatedAt(),
                passengerEntity.getUpdatedAt()
        );
    }
}
