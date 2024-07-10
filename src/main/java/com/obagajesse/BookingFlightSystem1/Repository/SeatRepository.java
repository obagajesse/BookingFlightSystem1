package com.obagajesse.BookingFlightSystem1.Repository;

import com.obagajesse.BookingFlightSystem1.DTO.Seat;
import com.obagajesse.BookingFlightSystem1.Entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
    SeatEntity findBySeatNumber(String seatNumber);
}
