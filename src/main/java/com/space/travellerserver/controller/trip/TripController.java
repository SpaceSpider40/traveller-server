package com.space.travellerserver.controller.trip;

import java.util.List;

import com.space.travellerserver.service.trip.AttendeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.space.travellerserver.dto.trip.TripAddAttendeeDto;
import com.space.travellerserver.dto.trip.TripAddRouteDto;
import com.space.travellerserver.dto.trip.TripCreationDto;
import com.space.travellerserver.dto.trip.attendee.AttendeeStatusDto;
import com.space.travellerserver.entity.attendee.Attendee;
import com.space.travellerserver.entity.trip.Route;
import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.entity.trip.Waypoint;
import com.space.travellerserver.service.trip.RouteService;
import com.space.travellerserver.service.trip.TripService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/trip")
public class TripController {

    private final TripService tripService;
    private final AttendeeService attendeeService;
    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<Trip> add(@RequestBody TripCreationDto dto){
        return ResponseEntity.ok(tripService.createTrip(dto));
    }

    @PostMapping("/{tripId}/attendee")
    public ResponseEntity<Trip> addAttendee(@PathVariable Long tripId, @RequestBody TripAddAttendeeDto dto){
        return ResponseEntity.ok(attendeeService.addAttendee(tripId, dto));
    }

    @PostMapping("/{tripId}/attendee/{attendeeId}/status")
    public ResponseEntity<Attendee> changeAttendeeStatus(@PathVariable Long tripId, @PathVariable Long attendeeId, @RequestBody AttendeeStatusDto dto) {
        return ResponseEntity.ok(attendeeService.changeStatus(tripId, attendeeId, dto));
    }
    
    @DeleteMapping("/{tripId}/attendee/{attendeeId}")
    public ResponseEntity<Boolean> removeAttendee(@PathVariable Long tripId, @PathVariable Long attendeeId){
        return ResponseEntity.ok(attendeeService.removeAttendee(tripId, attendeeId));
    }

    @PostMapping("/{tripId}/route")
    public ResponseEntity<Trip> addRoute(@PathVariable Long tripId, @RequestBody TripAddRouteDto dto) {
        return ResponseEntity.ok(tripService.addRoute(tripId, dto));
    }

    @PostMapping("/{tripId}/route/{routeId}/waypoint")
    public ResponseEntity<Route> addWaypoint(@PathVariable Long tripId, @PathVariable Long routeId, @RequestBody List<Waypoint> waypoint){
        return ResponseEntity.ok(routeService.addWaypoint(tripId, routeId, waypoint));
    }

    @GetMapping
    public ResponseEntity<List<Trip>> all(){
        return ResponseEntity.ok(tripService.getTrips());
    }
}
