package com.popcorn.feignclient;

import com.popcorn.request.PaymentRequest;
import com.popcorn.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payments-client",
        url = "${external-service.payments-ms.host}"
)
public interface PaymentsClient {
    @PostMapping(value = "${external-service.payments-ms.make-payment-uri}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest);
}
