package com.obagajesse.BookingFlightSystem1.Repository;

import com.obagajesse.BookingFlightSystem1.DTO.Booking;
import com.obagajesse.BookingFlightSystem1.Entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
}
