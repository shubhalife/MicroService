package com.neo.OrderService.service;


import com.neo.OrderService.entity.OrderEntity;
import com.neo.OrderService.exception.CustomException;
import com.neo.OrderService.external.client.PaymentServiceClient;
import com.neo.OrderService.external.client.ProductServiceClient;
import com.neo.OrderService.external.request.PaymentRequest;
import com.neo.OrderService.model.OrderRequest;
import com.neo.OrderService.model.OrderResponse;
import com.neo.OrderService.respository.OrderRespository;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.UUID;


@Service
@Log4j2
public class OrderServiceImpl  implements OrderService{

    @Autowired
    private OrderRespository orderRespository;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private PaymentServiceClient paymentServiceClient;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public UUID placeOrder(OrderRequest orderRequest) {

        productServiceClient.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        OrderEntity orderEntity = OrderEntity.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        orderEntity = orderRespository.save(orderEntity);

        PaymentRequest paymentRequest =PaymentRequest.builder()
                .amount(orderEntity.getAmount())
                .orderId(orderEntity.getOrderId())
                .paymentMode(orderRequest.getPaymentMode())
                .build();

        String orderStatus = null;

        try {
            paymentServiceClient.doPayment(paymentRequest);
            orderStatus ="PLACED";
        }catch (Exception e){
            orderStatus ="PAYMENT_FAILED";
            new CustomException("Payment not done","PAYMENT_NOT_DONE", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        orderEntity.setOrderStatus(orderStatus);

        orderRespository.save(orderEntity);

        log.info("Order placed Successfully with OrderID : {}", orderEntity.getOrderId());
        return orderEntity.getOrderId();
    }

    @Override
    public OrderResponse orderDetails(UUID orderId) {
        OrderEntity orderEntity = orderRespository.findById(orderId)
                .orElseThrow(() -> new CustomException(
                        "Order not found",
                        "ORDER_NOT_FOUND",
                        HttpStatus.NOT_FOUND.value()));
        log.info("fetch product with id: {}",orderEntity.getProductId());

       OrderResponse.ProductDetails productDetails = restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+orderEntity.getProductId(),OrderResponse.ProductDetails.class);



        OrderResponse orderResponse = OrderResponse.builder()
                .amount(orderEntity.getAmount())
                .orderId(orderEntity.getOrderId())
                .orderStatus(orderEntity.getOrderStatus())
                .ordeDate(orderEntity.getOrderDate())
                .productDetails(productDetails)
                .build();

        return orderResponse;
    }
}
