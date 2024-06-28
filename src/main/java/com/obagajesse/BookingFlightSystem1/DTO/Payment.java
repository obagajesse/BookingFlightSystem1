package com.obagajesse.BookingFlightSystem1.DTO;

import com.obagajesse.BookingFlightSystem1.Enum.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private Long id;
    private Long bookingId;
    private Long userId;
    private Double amount;
    private String currency;
    private PaymentStatus status;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
