package com.neo.productService.service;

import com.neo.productService.entity.ProductEntity;
import com.neo.productService.model.ProductRequest;
import com.neo.productService.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public UUID addProduct(ProductRequest productRequest) {

        ProductEntity productEntity
                = ProductEntity.builder()
                .productName(productRequest.getProductName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(productEntity);
        log.info("product {} with id {} added",productEntity.getProductName(),productEntity.getProductId());
        return productEntity.getProductId();
    }
}
