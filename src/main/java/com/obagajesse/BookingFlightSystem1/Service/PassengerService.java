package com.obagajesse.BookingFlightSystem1.Service;

import com.obagajesse.BookingFlightSystem1.DTO.Passenger;

import java.util.List;

public interface PassengerService {

    Passenger createPassenger(Passenger passenger);

    List<Passenger> getAllPassengers();

    Passenger getPassengerById(Long id);

    Passenger updatePassenger(Passenger passenger);

    void deletePassenger(Long id);
}
