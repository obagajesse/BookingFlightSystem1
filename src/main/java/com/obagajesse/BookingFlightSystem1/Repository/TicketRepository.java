package com.obagajesse.BookingFlightSystem1.Repository;

import com.obagajesse.BookingFlightSystem1.Entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity,Long> {
}
