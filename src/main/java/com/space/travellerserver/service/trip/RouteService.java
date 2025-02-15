package com.space.travellerserver.service.trip;

import org.springframework.stereotype.Service;

import com.space.travellerserver.dto.trip.route.RouteAddRoutePartDto;
import com.space.travellerserver.entity.trip.Route;
import com.space.travellerserver.repositiory.trip.TripRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RouteService {

    private final TripRepository tripRepository;

    public Route addPart(Long tripId, Long routeId, RouteAddRoutePartDto dto) {
        
    }

}
