package com.neo.paymentService.controller;

import com.neo.paymentService.model.PaymentRequest;
import com.neo.paymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

@Autowired
private PaymentService paymentService;

@PostMapping
public ResponseEntity<UUID> doPayment(@RequestBody PaymentRequest paymentRequest){

    return new ResponseEntity<>(paymentService.doPayment(paymentRequest), HttpStatus.ACCEPTED );
}


}
