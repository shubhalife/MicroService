package com.neo.OrderService.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ErrorResponse {

    private String message;

    private String errorCode;
}
