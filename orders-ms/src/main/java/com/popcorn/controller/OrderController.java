package com.popcorn.controller;

import com.google.gson.Gson;
import com.popcorn.common.OrderStatus;
import com.popcorn.common.PaymentMode;
import com.popcorn.feignclient.InventoryClient;
import com.popcorn.feignclient.PaymentsClient;
import com.popcorn.feignclient.UsersClient;
import com.popcorn.request.PackOrderRequest;
import com.popcorn.request.PaymentRequest;
import com.popcorn.request.PlaceOrderRequest;
import com.popcorn.response.PlaceOrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {
    private final UsersClient usersClient;
    private final InventoryClient inventoryClient;
    private final PaymentsClient paymentsClient;
    private final Gson jsonHelper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaceOrderResponse> placeOrder(@Valid @RequestBody PlaceOrderRequest orderRequest) {
        log.info("OrderController::placeOrder orderDetails={}", jsonHelper.toJson(orderRequest));
        var userResponse = usersClient.getUserByUserId(orderRequest.getUserId());
        BigDecimal cartValue = BigDecimal.ZERO;
        for(Long productId: orderRequest.getProductIds()) {
            var productResponse = inventoryClient.getProductByProductId(productId);
            if(productResponse.getBody() != null) {
                cartValue = cartValue.add(productResponse.getBody().getUnitPrice());
            }
        }
        var orderId = UUID.randomUUID();
        var paymentRequest = PaymentRequest.builder().amount(cartValue).paymentMode(PaymentMode.UPI).build();
        var paymentResponse = paymentsClient.makePayment(paymentRequest);
        assert paymentResponse.getBody() != null;
        var placeOrderResponse = PlaceOrderResponse.builder()
                .orderId(orderId)
                .transactionGUID(paymentResponse.getBody().getTransactionGUID())
                .orderStatus(OrderStatus.PLACED).build();

        var packOrderRequest = PackOrderRequest.builder()
                .orderId(orderId).deliveryAddress(orderRequest.getDeliveryAddress())
                .productId(orderRequest.getProductIds().getFirst())
                .build();
        inventoryClient.pack(packOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(placeOrderResponse);
    }
}
