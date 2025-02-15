package com.space.travellerserver.controller.trip;

import java.util.List;

import com.space.travellerserver.entity.attendee.Attendee;
import com.space.travellerserver.service.trip.AttendeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.space.travellerserver.dto.trip.TripAddAttendeeDto;
import com.space.travellerserver.dto.trip.TripAddRouteDto;
import com.space.travellerserver.dto.trip.TripCreationDto;
import com.space.travellerserver.dto.trip.route.RouteAddRoutePartDto;
import com.space.travellerserver.entity.trip.Route;
import com.space.travellerserver.entity.trip.RoutePart;
import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.service.trip.RouteService;
import com.space.travellerserver.service.trip.TripService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/trip")
public class TripController {

    private final TripService tripService;
    private final AttendeeService attendeeService;
    private final RouteService routeService;

    @PostMapping("/new")
    public ResponseEntity<Trip> add(@RequestBody TripCreationDto dto){
        return ResponseEntity.ok(tripService.createTrip(dto));
    }

    @PostMapping("/{tripId}/attendee/add")
    public ResponseEntity<Trip> addAttendee(@PathVariable Long tripId, @RequestBody TripAddAttendeeDto dto){
        return ResponseEntity.ok(attendeeService.addAttendee(tripId, dto));
    }

    @PostMapping("/{tripId}/route/add")
    public ResponseEntity<Trip> addRoute(@PathVariable Long tripId, @RequestBody TripAddRouteDto dto) {
        return ResponseEntity.ok(tripService.addRoute(tripId, dto));
    }

    @PostMapping("{tripId}/route/{routeId}/part/add")
    public ResponseEntity<Route> addRoutePart(@PathVariable Long tripId, @PathVariable Long routeId, @RequestBody RouteAddRoutePartDto dto) {
        return ResponseEntity.ok(routeService.addPart(tripId, routeId, dto));
    }
    

    @GetMapping()
    public ResponseEntity<List<Trip>> all(){
        return ResponseEntity.ok(tripService.getTrips());
    }
}
