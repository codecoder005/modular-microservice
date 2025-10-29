package com.popcorn.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipConsignmentRequest {
    @NotNull
    private UUID orderId;
    @NotNull
    private AddressDTO deliveryAddress;
}
