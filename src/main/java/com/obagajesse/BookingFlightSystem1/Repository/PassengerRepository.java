package com.obagajesse.BookingFlightSystem1.Repository;

import com.obagajesse.BookingFlightSystem1.DTO.Passenger;
import com.obagajesse.BookingFlightSystem1.Entity.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity,Long> {
}
