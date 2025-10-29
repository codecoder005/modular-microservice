package com.popcorn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long productId;
    private String name;
    private String description;
    private String sellerId;
    private BigDecimal unitPrice;
    private String specifications;
    private Long availableProducts;
}
