package com.obagajesse.BookingFlightSystem1.Mapper;

import com.obagajesse.BookingFlightSystem1.DTO.Payment;
import com.obagajesse.BookingFlightSystem1.Entity.PaymentEntity;

public class PaymentMapper {

    public static PaymentEntity mapToPaymentEntity(Payment payment){
        return new PaymentEntity(
                payment.getId(),
                payment.getBookingId(),
                payment.getUserId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getPaymentMethod(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }

    public static Payment mapToPayment(Payment paymentEntity){
        return new Payment(
               paymentEntity.getId(),
               paymentEntity.getBookingId(),
               paymentEntity.getUserId(),
               paymentEntity.getAmount(),
               paymentEntity.getCurrency(),
               paymentEntity.getStatus(),
                paymentEntity.getPaymentMethod(),
                paymentEntity.getCreatedAt(),
                paymentEntity.getUpdatedAt()
        );
    }
}
