package com.popcorn;

import com.popcorn.entity.UserEntity;
import com.popcorn.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class UsersMicroservice {
    public static void main(String[] args) {
        SpringApplication.run(UsersMicroservice.class, args);
    }

    @Bean
    @Profile("local")
    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            var john = UserEntity.builder().name("John Doe").email("john.doe@email.com").build();
            userRepository.save(john);
        };
    }
}