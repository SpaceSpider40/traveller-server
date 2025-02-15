package com.space.travellerserver.entity.trip;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"route"})
public class RoutePart {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(targetEntity = Route.class)
    private Route route;

    @Column(nullable = true)
    private Float distance;

    @OneToMany(mappedBy = "routePart", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("routePart")
    private List<Waypoint> waypoints;
}
