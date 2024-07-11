package com.obagajesse.BookingFlightSystem1.Service;

import com.obagajesse.BookingFlightSystem1.DTO.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {

    Seat createSeat(Seat seat);

    List<Seat> getAllSeats();

    Seat getSeatByNumber(String seatNumber);

    Seat getSeatById(Long id);

    List<Seat> getSeatsByFlightId(Long flightId);

    Seat updateSeat(Seat seat);

    void deleteSeat(Long id);

    boolean checkSeatAvailability(Long flightId,String seatNumber);

    Seat bookSeat(Long flightId,String seatNumber);
}
