package com.space.travellerserver.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.space.travellerserver.dto.TripDto;
import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.entity.trip.TripStatus;
import com.space.travellerserver.repositiory.UserRepository;
import com.space.travellerserver.repositiory.trip.TripRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public List<Trip> getTrips(){
        // get all trips from the database
        return tripRepository.findAll();
    }

    public void createTrip(TripDto tripDto){
        // create a new Trip entity and save it to the database

        Trip trip = Trip.builder()
                    .owner(userRepository.findById(tripDto.getOwnerId()).get())
                    .title(tripDto.getTitle())
                    .description(tripDto.getDescription())
                    .firstDay(tripDto.getFirstDay())
                    .lastDay(tripDto.getLastDay())
                    .tripStatus(TripStatus.PLANNED)
                    .build();

        tripRepository.save(trip);
        tripRepository.flush();
    }
}
