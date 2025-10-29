package com.popcorn.controller;

import com.popcorn.common.PaymentStatus;
import com.popcorn.request.PaymentRequest;
import com.popcorn.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private static final Map<UUID, PaymentRequest> PAYMENTS_MAP = new LinkedHashMap<>();

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest) {
        log.info("PaymentController::makePayment amount={}", paymentRequest.getAmount());
        var transactionGUID = UUID.randomUUID();
        PAYMENTS_MAP.put(transactionGUID, paymentRequest);
        var response = PaymentResponse.builder()
                .paymentStatus(PaymentStatus.COMPLETED)
                .transactionGUID(transactionGUID)
                .build();
        log.info("PaymentController::makePayment payment successful");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
