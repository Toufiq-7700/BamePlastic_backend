package com.BamePlastic.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "buses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_lane")
    private Integer currentLane; // Values: 1, 2, or 3

    private Double speed;

    private Integer capacity;

    @Column(name = "current_passenger_count")
    private Integer currentPassengerCount;

    @Column(name = "bus_condition")
    private String condition; // Values: NORMAL, OVERLOADED, WATERLOGGED

    @OneToOne(mappedBy = "bus")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private GameSession gameSession;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private java.util.List<Passenger> passengers;
}
