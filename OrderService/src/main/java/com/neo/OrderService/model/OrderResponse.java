package com.neo.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private UUID orderId;

    private Instant ordeDate;

    private long amount;

    private String orderStatus;

    private ProductDetails productDetails;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDetails {

        private UUID productId;

        private  String productName;

        private  long price;

        private long quantity;
    }


}
