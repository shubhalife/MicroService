package com.neo.paymentService.service;

import com.neo.paymentService.entity.TransactionDetails;
import com.neo.paymentService.model.PaymentRequest;
import com.neo.paymentService.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Log4j2
public class PaymentServiceImpl implements  PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public UUID doPayment(PaymentRequest paymentRequest) {

        TransactionDetails transactionDetails= TransactionDetails.builder()
                .paymentMode(paymentRequest.getPaymentMode().toString())
                .orderId(paymentRequest.getOrderId())
                .referenceNumber((paymentRequest.getReferenceNumber()))
                .paymentDate(Instant.now())
                .paymentStatus("SUCCESS")
                .amount(paymentRequest.getAmount())
                .build();

        paymentRepository.save(transactionDetails);

        log.info("Transaction save with id : {}",transactionDetails.getId());
        return transactionDetails.getId();
    }
}
