package com.obagajesse.BookingFlightSystem1.Service;

import com.obagajesse.BookingFlightSystem1.DTO.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {

    Payment createPayment(Payment payment);

    List<Payment> getAllPayments();

    Payment getPaymentById(Long id);

    Payment updatePayment(Payment payment);

    void deletePayment(Long id);
}
