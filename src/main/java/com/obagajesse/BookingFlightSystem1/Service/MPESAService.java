package com.obagajesse.BookingFlightSystem1.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.obagajesse.BookingFlightSystem1.DTO.Payment;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
public class MPESAService {

    @Value("${mpesa.consumerKey}")
    private String consumerKey;

    @Value("${mpesa.consumerSecret}")
    private String consumerSecret;

    @Value("${mpesa.shortCode}")
    private String shortCode;

    @Value("${mpesa.passKey}")
    private String passKey;

    @Value("${mpesa.apiBaseUrl}")
    private String apiBaseUrl;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public MPESAService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.baseUrl(apiBaseUrl).build();
        this.objectMapper = objectMapper;
    }

    private Mono<String> getAccessToken(){
        return webClient.get()
                .uri("/oauth/v1/generate?grant_type=client_credentials")
                .headers(headers -> headers.setBasicAuth(consumerKey,consumerSecret))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> jsonNode.get("access_token").asText());
    }

    public Mono<JsonNode> initiatePayment(Payment payment) {
        return getAccessToken()
                .flatMap(accessToken -> {
                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                    String password = Base64.getEncoder().encodeToString((shortCode + passKey+ timestamp).getBytes());

                    JsonNode payload = objectMapper.createObjectNode()
                            .put("BusinessShortCode",shortCode)
                            .put("Password",password)
                            .put("Timestamp",timestamp)
                            .put("TransactionType","CustomerPayBillOnline")
                            .put("Amount",payment.getAmount())
                            .put("Party A",payment.getUserId())
                            .put("Party B",shortCode)
                            .put("Phone Number",payment.getUserId())
                            .put("CallBackURL","https://your-callback-url.com/callback")
                            .put("AccountReference",payment.getBookingId().toString())
                            .put("TransactionDesc","Payment for flight");

                    return webClient.post()
                            .uri("/mpesa/stkpush/v1/processrequest")
                            .headers(headers -> headers.setBearerAuth(accessToken))
                            .bodyValue(payment)
                            .retrieve()
                            .bodyToMono(JsonNode.class);
                });
    }
}
