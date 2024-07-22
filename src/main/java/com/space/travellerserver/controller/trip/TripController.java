package com.space.travellerserver.controller.trip;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.repositiory.trip.TripRepository;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripRepository tripRepository;

    public TripController(TripRepository tripRepository){
        this.tripRepository = tripRepository;
    }
    
    @PostMapping("/add")
    public void add(@RequestBody Trip trip){
        tripRepository.save(trip);
    }

    @GetMapping("/")
    public List<Trip> all(){
        return tripRepository.findAll();
    }

    
}
