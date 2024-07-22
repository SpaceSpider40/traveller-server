package com.space.travellerserver.entity.trip;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attedee {
    @Id
    @Column(unique=true, nullable=false)
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_trip", nullable = false)
    private Trip trip;
}
