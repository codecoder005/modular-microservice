package com.popcorn.feignclient;

import com.popcorn.request.ShipConsignmentRequest;
import com.popcorn.response.ShipConsignmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "logistics-client",
        url = "${external-service.logistics-ms.host}",
        path = "${external-service.logistics-ms.logistics-uri}"
)
public interface LogisticsClient {
    @PostMapping(value = "/ship", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ShipConsignmentResponse> shipConsignment(@RequestBody ShipConsignmentRequest shipConsignmentRequest);
}
