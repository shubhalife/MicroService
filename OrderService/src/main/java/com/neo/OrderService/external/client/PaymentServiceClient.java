package com.neo.OrderService.external.client;

import com.neo.OrderService.exception.CustomException;
import com.neo.OrderService.external.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@CircuitBreaker(name = "external",fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentServiceClient {

    @PostMapping
    public ResponseEntity<UUID> doPayment(@RequestBody PaymentRequest paymentRequest);

    default void fallback(Exception exception){
        throw new CustomException("Payment Service is unavailable","UNAVAILABLE",500);

    }
}
