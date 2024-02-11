package com.neo.productService.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductRequest {

    private  String productName;

    private  long price;

    private long quantity;
}
