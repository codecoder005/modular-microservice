package com.popcorn.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "consignments")
public class ConsignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID consignmentId;
    @Column(nullable = false)
    private UUID orderId;
}
