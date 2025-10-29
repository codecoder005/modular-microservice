package com.popcorn.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipConsignmentResponse {
    private UUID consignmentId;
    private UUID orderId;
}
