package com.obagajesse.BookingFlightSystem1.Service;

import com.obagajesse.BookingFlightSystem1.DTO.Seat;
import org.springframework.stereotype.Service;

@Service
public interface SeatService {

    Seat createSeat(Seat seat);

    Seat[] getAllSeats();

    Seat getSeatById(Long id);

    Seat updateSeat(Seat seat);

    void deleteSeat(Long id);
}
