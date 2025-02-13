package com.space.travellerserver.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.space.travellerserver.dto.trip.TripAddAttendeeDto;
import com.space.travellerserver.dto.trip.TripAddRouteDto;
import com.space.travellerserver.dto.trip.TripCreationDto;
import com.space.travellerserver.entity.Icon;
import com.space.travellerserver.entity.attendee.Attendee;
import com.space.travellerserver.entity.attendee.AttendeeStatus;
import com.space.travellerserver.entity.trip.Route;
import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.entity.trip.TripStatus;
import com.space.travellerserver.entity.user.User;
import com.space.travellerserver.repositiory.IconRepository;
import com.space.travellerserver.repositiory.UserRepository;
import com.space.travellerserver.repositiory.trip.TripRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final IconRepository iconRepository;

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

        tripRepository.saveAndFlush(trip);

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

        if (user.getId() == trip.getOwner().getId()) {
            throw new IllegalArgumentException("Owner cannot be an attendee");
        }

        trip.getAttendees().stream()
                .filter(attendee -> attendee.getUser().getId() == user.getId())
                .findAny()
                .ifPresent(attendee -> {
                    throw new IllegalArgumentException("User is already an attendee");
                });

        Attendee attendee = Attendee.builder()
                .user(user)
                .trip(trip)
                .attendeeStatus(AttendeeStatus.INVITED)
                .build();

        trip.getAttendees().add(attendee);

        tripRepository.saveAndFlush(trip);

        return trip;
    }

    public Trip addRoute(TripAddRouteDto dto) {
        Optional<Trip> tripOption = tripRepository.findById(dto.getTripId());

        if(!tripOption.isPresent()){
            throw new IllegalArgumentException("Trip not found");
        }

        Trip trip = tripOption.get();

        Route route = Route.builder()
                        .trip(trip)
                        .title(dto.getTitle())
                        .description(dto.getDescription())
                        .startDate(dto.getStartDate())
                        .endDate(dto.getEndDate())
                        .build();

        Optional<Icon> iconOption = iconRepository.findByName(dto.getIconName());
        if (iconOption.isPresent()) {
            route.setIcon(iconOption.get());
        }

        trip.getRoutes().add(route);

        tripRepository.saveAndFlush(trip);

        return trip;
    }
}
