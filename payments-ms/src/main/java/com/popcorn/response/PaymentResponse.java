package com.popcorn.response;

import com.popcorn.common.PaymentStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private UUID transactionGUID;
    private PaymentStatus paymentStatus;
}
