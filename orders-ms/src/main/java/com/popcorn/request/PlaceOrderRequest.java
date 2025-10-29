package com.popcorn.request;

import com.popcorn.common.PaymentMode;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderRequest {
    @NotNull
    @Size(min = 1)
    private List<Long> productIds;
    @NotNull
    private Long userId;
    @NotNull
    private AddressDTO deliveryAddress;
    @NotNull
    private PaymentMode paymentMode;
}
