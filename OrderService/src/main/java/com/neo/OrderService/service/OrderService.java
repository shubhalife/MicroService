package com.neo.OrderService.service;


import com.neo.OrderService.model.OrderRequest;
import com.neo.OrderService.model.OrderResponse;

import java.util.UUID;

public interface OrderService {
    public UUID placeOrder(OrderRequest orderRequest);

    OrderResponse orderDetails(UUID orderId);
}
