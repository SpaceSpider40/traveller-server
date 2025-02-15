package com.space.travellerserver.service.trip;

import org.springframework.stereotype.Service;

import com.space.travellerserver.entity.trip.Route;
import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.entity.trip.Waypoint;
import com.space.travellerserver.repositiory.trip.TripRepository;

import io.jsonwebtoken.lang.Arrays;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RouteService {

    private final TripRepository tripRepository;

    public Route addWaypoint(Long tripId, Long routeId, Waypoint waypoint) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        Route route = trip.getRoutes().stream().filter(r -> r.getId().equals(routeId)).findFirst()
                .orElseThrow(() -> new RuntimeException("Route not found"));

        route.getWaypoints().add(waypoint);

        tripRepository.saveAndFlush(trip);

        return route;
    }

    public Route addWaypoint(Long tripId, Long routeId, Waypoint[] waypoints) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        Route route = trip.getRoutes().stream().filter(r -> r.getId().equals(routeId)).findFirst()
                .orElseThrow(() -> new RuntimeException("Route not found"));

        route.getWaypoints().addAll(Arrays.asList(waypoints));

        tripRepository.saveAndFlush(trip);

        return route;
    }
}
