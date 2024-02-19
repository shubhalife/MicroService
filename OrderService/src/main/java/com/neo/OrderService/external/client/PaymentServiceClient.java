package com.neo.OrderService.external.client;

import com.neo.OrderService.external.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentServiceClient {

    @PostMapping
    public ResponseEntity<UUID> doPayment(@RequestBody PaymentRequest paymentRequest);
}
