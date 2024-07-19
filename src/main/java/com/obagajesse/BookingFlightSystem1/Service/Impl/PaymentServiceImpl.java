package com.obagajesse.BookingFlightSystem1.Service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.obagajesse.BookingFlightSystem1.DTO.Payment;
import com.obagajesse.BookingFlightSystem1.Entity.PaymentEntity;
import com.obagajesse.BookingFlightSystem1.Enum.PaymentStatus;
import com.obagajesse.BookingFlightSystem1.Mapper.PaymentMapper;
import com.obagajesse.BookingFlightSystem1.Repository.PaymentRepository;
import com.obagajesse.BookingFlightSystem1.Service.MPESAService;
import com.obagajesse.BookingFlightSystem1.Service.PaymentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final MPESAService mpesaService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, MPESAService mpesaService){
        this.paymentRepository = paymentRepository;
        this.mpesaService = mpesaService;
    }

    @Override
    public Payment createPayment(Payment payment){
        PaymentEntity paymentEntity = PaymentMapper.mapToPaymentEntity(payment);
        paymentEntity.setCreatedAt(LocalDateTime.now());
        PaymentEntity savedPaymentEntity = paymentRepository.save(paymentEntity);

        Mono<JsonNode> response = mpesaService.initiatePayment(payment);

        response.subscribe(
                res -> {
                    System.out.println("MPESA Response:" + res);

                    String responseCode = res.get("ResponseCode").asText();
                    if("0".equals(responseCode)){
                        savedPaymentEntity.setStatus(PaymentStatus.PAID);
                    }else{
                        savedPaymentEntity.setStatus(PaymentStatus.CANCELLED);
                    }
                    paymentRepository.save(savedPaymentEntity);
                },
                err -> {
                    System.err.println("Error During MPESA Payment Initiation:" + err.getMessage());
                    savedPaymentEntity.setStatus(PaymentStatus.CANCELLED);
                    paymentRepository.save(savedPaymentEntity);
                }
        );

        return PaymentMapper.mapToPayment(savedPaymentEntity);
    }

    @Override
    public List<Payment> getAllPayments(){
        List<PaymentEntity> paymentEntities = paymentRepository.findAll();
        return paymentEntities.stream().map(PaymentMapper::mapToPayment).toList();
    }

    @Override
    public Payment getPaymentById(Long id){
        return paymentRepository.findById(id).map(PaymentMapper::mapToPayment).orElse(null);
    }

    @Override
    public Payment updatePayment(Payment payment){
        PaymentEntity paymentEntity = PaymentMapper.mapToPaymentEntity(payment);
        paymentEntity.setUpdatedAt(LocalDateTime.now());
        PaymentEntity updatedPaymentEntity = paymentRepository.save(paymentEntity);
        return PaymentMapper.mapToPayment(updatedPaymentEntity);
    }

    @Override
    public void deletePayment(Long id){
        paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment cancelled"));
    }
}
