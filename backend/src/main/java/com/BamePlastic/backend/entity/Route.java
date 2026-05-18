package com.BamePlastic.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "start_point")
    private String startPoint;

    @Column(name = "end_point")
    private String endPoint;

    @Column(name = "total_stops")
    private Integer totalStops;

    private Double distance; // in km

    @Column(name = "base_fare")
    private Double baseFare;

    @OneToMany(mappedBy = "route")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private java.util.List<GameSession> sessions;
}
