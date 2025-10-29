package com.popcorn.repository;

import com.popcorn.entity.ConsignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConsignmentRepository extends JpaRepository<ConsignmentEntity, Long> {
    Optional<ConsignmentEntity> findByOrderIdEquals(UUID orderId);
}
