package com.BamePlastic.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "passengers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String type; // Values: STUDENT, REGULAR, ELDERLY

    @Builder.Default
    @Column(name = "has_paid", nullable = false)
    private Boolean hasPaid = false;

    @Column(name = "fare_amount")
    private Double fareAmount;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Builder.Default
    @Column(name = "is_dodger", nullable = false)
    private Boolean isDodger = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    private Bus bus;
}
