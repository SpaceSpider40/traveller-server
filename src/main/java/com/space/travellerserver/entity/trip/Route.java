package com.space.travellerserver.entity.trip;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.space.travellerserver.entity.Icon;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"trip"})
public class Route {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    private String title;
    private String description;

    @OneToOne(targetEntity = Icon.class)
    private Icon icon;

    @ManyToOne(targetEntity = Trip.class)
    private Trip trip;

    private Instant startDate;
    private Instant endDate;

    @Column(nullable = true)
    private Float totalDistance;

    @OneToMany(mappedBy = "route", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("route")
    final List<RoutePart> parts = new ArrayList<>();
}
