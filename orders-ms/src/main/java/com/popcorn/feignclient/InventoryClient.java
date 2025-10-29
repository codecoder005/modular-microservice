package com.popcorn.feignclient;

import com.popcorn.request.PackOrderRequest;
import com.popcorn.request.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "inventory-client",
        url = "${external-service.inventory-ms.host}",
        path = "${external-service.inventory-ms.products-uri}"
)
public interface InventoryClient {
    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDTO> getProductByProductId(@PathVariable(name = "productId") final Long productId);

    @PostMapping(value = "/pack", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> pack(@RequestBody PackOrderRequest packOrderRequest);
}
