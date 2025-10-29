package com.popcorn.controller;

import com.google.gson.Gson;
import com.popcorn.entity.ConsignmentEntity;
import com.popcorn.repository.ConsignmentRepository;
import com.popcorn.request.ShipConsignmentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/logistics")
@RequiredArgsConstructor
@Slf4j
public class LogisticsController {
    private final ConsignmentRepository consignmentRepository;
    private final Gson jsonHelper;

    @GetMapping(value = "/track/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsignmentEntity> trackConsignment(@PathVariable(name = "orderId") final UUID orderId) {
        log.info("LogisticsController::trackConsignment");
        return consignmentRepository.findByOrderIdEquals(orderId)
                .map(consignment -> ResponseEntity.status(HttpStatus.OK).body(consignment))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(value = "/ship", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsignmentEntity> shipConsignment(@Valid @RequestBody ShipConsignmentRequest shipRequest) {
        log.info("LogisticsController::shipConsignment request={}", jsonHelper.toJson(shipRequest));
        var newConsignment = ConsignmentEntity.builder().orderId(shipRequest.getOrderId()).build();
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(consignmentRepository.save(newConsignment));
    }
}
