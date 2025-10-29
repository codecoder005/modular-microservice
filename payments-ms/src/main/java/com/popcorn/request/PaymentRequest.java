package com.popcorn.request;

import com.popcorn.common.PaymentMode;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
    private Long userId;
    private BigDecimal amount;
    private PaymentMode paymentMode;
}
