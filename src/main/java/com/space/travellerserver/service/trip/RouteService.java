package com.space.travellerserver.service.trip;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.space.travellerserver.entity.trip.Route;
import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.entity.trip.Waypoint;
import com.space.travellerserver.repositiory.trip.TripRepository;

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
        route.setTotalDistance(calculateDistance(List.of(waypoint)));

        trip.setModificationDate(Instant.now());

        tripRepository.saveAndFlush(trip);

        return route;
    }

    public Route addWaypoint(Long tripId, Long routeId, List<Waypoint> waypoints) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        Route route = trip.getRoutes().stream().filter(r -> r.getId().equals(routeId)).findFirst()
                .orElseThrow(() -> new RuntimeException("Route not found"));

        waypoints.forEach(waypoint -> waypoint.setRoute(route));

        route.getWaypoints().addAll(waypoints);
        route.setTotalDistance(calculateDistance(waypoints));

        trip.setModificationDate(Instant.now());

        tripRepository.saveAndFlush(trip);

        return route;
    }

    private float calculateDistance(List<Waypoint> waypoints) {
        return waypoints.size();
    }
}
