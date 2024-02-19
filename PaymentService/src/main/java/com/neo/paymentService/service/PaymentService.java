package com.neo.paymentService.service;

import com.neo.paymentService.model.PaymentRequest;

import java.util.UUID;

public interface PaymentService {
   public UUID doPayment(PaymentRequest paymentRequest);
}
