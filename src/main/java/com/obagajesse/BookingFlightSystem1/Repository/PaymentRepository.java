package com.obagajesse.BookingFlightSystem1.Repository;

import com.obagajesse.BookingFlightSystem1.DTO.Payment;
import com.obagajesse.BookingFlightSystem1.Entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
}
