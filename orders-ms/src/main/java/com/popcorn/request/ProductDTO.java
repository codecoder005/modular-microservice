package com.popcorn.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private String sellerId;
    private BigDecimal unitPrice;
    private String specifications;
}
