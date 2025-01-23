package com.space.travellerserver.controller.trip;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.space.travellerserver.dto.TripDto;
import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.service.TripService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @PostMapping("/")
    public void add(@RequestBody TripDto trip){
        tripService.createTrip(trip);
    }

    @GetMapping("/")
    public List<Trip> all(){
        return tripService.getTrips();
    }
    
    
}
