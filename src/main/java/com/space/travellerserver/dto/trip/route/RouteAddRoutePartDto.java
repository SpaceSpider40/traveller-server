package com.space.travellerserver.dto.trip.route;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;

@Data
public class RouteAddRoutePartDto {
    private Long routeId;
    private Float distance;

    @JsonCreator
    public RouteAddRoutePartDto(Long routeId, Float distance) {
        this.routeId = routeId;
        this.distance = distance;
    }
}
