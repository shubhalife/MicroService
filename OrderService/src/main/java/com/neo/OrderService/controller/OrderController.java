package com.neo.OrderService.controller;

import com.neo.OrderService.model.OrderRequest;
import com.neo.OrderService.model.OrderResponse;
import com.neo.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<UUID> placeOrder(@RequestBody OrderRequest orderRequest){
    UUID orderId = orderService.placeOrder(orderRequest);
    log.info("Placing Order...");
    return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

    @GetMapping("/{OrderId}")
    public ResponseEntity<OrderResponse> orderDetails(@PathVariable UUID OrderId){

        OrderResponse orderResponse = orderService.orderDetails(OrderId);

        return new ResponseEntity<>(orderResponse,HttpStatus.OK);

    }

}
