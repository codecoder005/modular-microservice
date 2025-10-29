package com.popcorn.response;

import com.popcorn.common.OrderStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderResponse {
    private OrderStatus orderStatus;
    private UUID orderId;
    private UUID transactionGUID;
}
