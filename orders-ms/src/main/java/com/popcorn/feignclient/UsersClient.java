package com.popcorn.feignclient;

import com.popcorn.response.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "users-client",
        url = "${external-service.users-ms.host}",
        path = "${external-service.users-ms.users-uri}"
)
public interface UsersClient {
    @GetMapping(value="/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> getUserByUserId(@PathVariable final Long userId);
}
