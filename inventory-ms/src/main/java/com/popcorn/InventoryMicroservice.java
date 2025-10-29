package com.popcorn;

import com.popcorn.entity.ProductEntity;
import com.popcorn.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class InventoryMicroservice {
    public static void main(String[] args) {
        SpringApplication.run(InventoryMicroservice.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            var iPhone = ProductEntity.builder()
                    .name("iPhone 17 Pro")
                    .description("iPhone 17 Pro")
                    .unitPrice(BigDecimal.valueOf(99999.00d))
                    .sellerId("APPLE")
                    .availableProducts(50L)
                    .build();
            productRepository.saveAndFlush(iPhone);
        };
    }
}