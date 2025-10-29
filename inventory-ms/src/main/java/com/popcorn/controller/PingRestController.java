package com.popcorn.controller;

import com.popcorn.response.PingAPIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class PingRestController {
    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PingAPIResponse> ping(
            @RequestHeader(name = "client-id") final String clientId,
            @RequestHeader(value = HttpHeaders.CACHE_CONTROL, required = false, defaultValue = "no-cache") String cacheControl
    ) {
        log.info("PingRestController::ping client-id={}, Cache-Control={}", clientId, cacheControl);
        var response = PingAPIResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(200)
                .message("Up and Running")
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
