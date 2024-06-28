package com.obagajesse.BookingFlightSystem1.Controller;

import com.obagajesse.BookingFlightSystem1.DTO.Payment;
import com.obagajesse.BookingFlightSystem1.ExceptionHandling.PaymentFailedException;
import com.obagajesse.BookingFlightSystem1.Service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments(){
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            throw new PaymentFailedException("Payment not found.");
        }
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){
        return new ResponseEntity<>(paymentService.createPayment(payment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
        return new ResponseEntity<>("Payment deleted.",HttpStatus.OK);
    }
}
