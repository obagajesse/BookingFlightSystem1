package com.obagajesse.BookingFlightSystem1.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.obagajesse.BookingFlightSystem1.Service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mpesa")
public class MPESAController {

    private final PaymentService paymentService;

    public MPESAController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Void> handleMpesaCallBack(@RequestBody JsonNode callBackData){
        System.out.println("MPESA Callback Data:" + callBackData);

        return ResponseEntity.ok().build();
    }
}
