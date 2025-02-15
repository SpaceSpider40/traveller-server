package com.space.travellerserver.entity.trip;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.space.travellerserver.entity.Icon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"routePart"})
public class Waypoint {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToOne(targetEntity = Icon.class)
    private Icon icon;

    @ManyToOne(targetEntity = Route.class)
    private Route route;

    private double longitude;
    private double latitude;

    public static float getDistance(Waypoint otherWaypoint){
        return 0;
    }
}
