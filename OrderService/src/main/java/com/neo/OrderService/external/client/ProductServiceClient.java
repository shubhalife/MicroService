package com.neo.OrderService.external.client;

import com.neo.OrderService.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
@CircuitBreaker(name = "external",fallbackMethod = "fallback")
@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductServiceClient {

    @PutMapping("/reduceQuantity/{uuid}")
    ResponseEntity<Void> reduceQuantity(
            @PathVariable("uuid") UUID uuid,
            @RequestParam long quantity);

    default void fallback(Exception exception){
        throw new CustomException("Product Service is unavailable","UNAVAILABLE",500);

    }
}
