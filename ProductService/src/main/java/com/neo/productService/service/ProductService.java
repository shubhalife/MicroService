package com.neo.productService.service;

import com.neo.productService.model.ProductRequest;

import java.util.UUID;

public interface ProductService {
    public UUID addProduct(ProductRequest productRequest);
}
