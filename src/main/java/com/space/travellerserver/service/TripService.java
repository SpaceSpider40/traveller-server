package com.space.travellerserver.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.space.travellerserver.dto.trip.TripAddAttendeeDto;
import com.space.travellerserver.dto.trip.TripCreationDto;
import com.space.travellerserver.entity.attendee.Attendee;
import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.entity.trip.TripStatus;
import com.space.travellerserver.entity.user.User;
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

    public Trip createTrip(TripCreationDto tripDto){
        // create a new Trip entity and save it to the database

        Trip trip = Trip.builder()
                    .owner(userRepository.findById(tripDto.getOwnerId()).get())
                    .title(tripDto.getTitle())
                    .description(tripDto.getDescription())
                    .firstDay(tripDto.getFirstDay())
                    .lastDay(tripDto.getLastDay())
                    .tripStatus(TripStatus.PLANNED)
                    .creationDate(Instant.now())
                    .modificationDate(Instant.now())
                    .build();

        tripRepository.save(trip);
        tripRepository.flush();

        return trip;
    }

    public Trip addAttendee(TripAddAttendeeDto dto) {
        
        Optional<User> userOption = userRepository.findById(dto.userId);
        Optional<Trip> tripOption = tripRepository.findById(dto.tripId);

        if(!userOption.isPresent() || !tripOption.isPresent()){
            throw new IllegalArgumentException("User or Trip not found");
        }

        User user = userOption.get();
        Trip trip = tripOption.get();

        Attendee attendee = Attendee.builder()
                .user(user)
                .trip(trip)
                .build();
        
        trip.getAttendees().add(attendee);
        tripRepository.save(trip);
        tripRepository.flush();

        return trip;
    }
}
