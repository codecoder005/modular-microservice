package com.popcorn.controller;

import com.google.gson.Gson;
import com.popcorn.entity.ProductEntity;
import com.popcorn.feignclient.LogisticsClient;
import com.popcorn.repository.ProductRepository;
import com.popcorn.request.PackOrderRequest;
import com.popcorn.request.ShipConsignmentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {
    private final LogisticsClient logisticsClient;
    private final ProductRepository productRepository;
    private final Gson jsonHelper;

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductEntity> getProductByProductId(@PathVariable(name = "productId") final Long productId) {
        log.info("ProductController::getProductByProductId productId={}", productId);
        var product = productRepository.findById(productId);
        return product.map(productEntity -> ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productEntity)
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(value = "/pack", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> pack(@Valid @RequestBody PackOrderRequest packOrderRequest) {
        log.info("ProductController::pack started packing {}", jsonHelper.toJson(packOrderRequest));
        var shipRequest = ShipConsignmentRequest.builder()
                .orderId(packOrderRequest.getOrderId())
                .deliveryAddress(packOrderRequest.getDeliveryAddress())
                .build();
        var shipOrderResponse = logisticsClient.shipConsignment(shipRequest);
        assert shipOrderResponse != null;
        log.info("ProductController::pack order shipped. {}", jsonHelper.toJson(shipOrderResponse.getBody()));
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
