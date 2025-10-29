package com.popcorn.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackOrderRequest {
    @NotNull
    private Long productId;
    @NotNull
    private UUID orderId;
    @NotNull
    private AddressDTO deliveryAddress;
}
