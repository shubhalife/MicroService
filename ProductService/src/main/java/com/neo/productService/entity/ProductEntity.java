package com.neo.productService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "product_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @Column(name = "PRODUCT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID productId;

    @Column(name = "PRODUCT_NAME")
    private  String productName;

    @Column(name = "PRODUCT_PRICE")
    private  long price;

    @Column(name = "PRODUCT_QUANTITY")
    private long quantity;


}
