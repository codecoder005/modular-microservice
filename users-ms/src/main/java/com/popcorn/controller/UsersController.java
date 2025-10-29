package com.popcorn.controller;

import com.popcorn.entity.UserEntity;
import com.popcorn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {
    private final UserRepository userRepository;
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> getUserByUserId(@PathVariable(name = "userId") final Long userId) {
        log.info("UsersController::getUserByUserId userId={}", userId);
        try{
            var user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user found with userId=" + userId));
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(user);
        }catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
