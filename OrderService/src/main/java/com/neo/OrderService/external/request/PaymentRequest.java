package com.neo.OrderService.external.request;

import com.neo.OrderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private UUID orderId;

    private long amount;

    private String referenceNumber;

    private PaymentMode paymentMode;
}
