package com.neo.OrderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductServiceClient {

    @PutMapping("/reduceQuantity/{uuid}")
    ResponseEntity<Void> reduceQuantity(
            @PathVariable("uuid") UUID uuid,
            @RequestParam long quantity);
}
