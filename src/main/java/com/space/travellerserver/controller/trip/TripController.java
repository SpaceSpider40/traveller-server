package com.space.travellerserver.controller.trip;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.space.travellerserver.dto.trip.TripAddAttendeeDto;
import com.space.travellerserver.dto.trip.TripCreationDto;
import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.service.TripService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @PostMapping("/new")
    public ResponseEntity<Trip> add(@RequestBody TripCreationDto dto){
        return ResponseEntity.ok(tripService.createTrip(dto));
    }

    @PostMapping("/addAttendee")
    public ResponseEntity<Trip> addAttendee(@RequestBody TripAddAttendeeDto dto){
        return ResponseEntity.ok(tripService.addAttendee(dto));
    }

    @GetMapping()
    public ResponseEntity<List<Trip>> all(){
        return ResponseEntity.ok(tripService.getTrips());
    }
}
