package com.space.travellerserver.service.trip;

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

    public List<Trip> getTrips() {
        // get all trips from the database
        return tripRepository.findAll();
    }

    public List<Trip> getTrips(Attendee attendee) {
        return tripRepository.findByAttendeesContaining(attendee);
    }

    public List<Trip> getTrips(Long userId) {
        return tripRepository.findAll().stream().filter(r -> {
            if (r.getOwner().getId() == userId) {
                return true;
            }

            if (r.getAttendees().stream().filter(a -> a.getUser().getId() == userId).findFirst().isPresent()) {
                return true;
            }

            return false;
        }).toList();
    }

    public Trip createTrip(TripCreationDto tripDto) {
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

    public Trip addRoute(Long tripId, TripAddRouteDto dto) {
        Optional<Trip> tripOption = tripRepository.findById(tripId);

        if (tripOption.isEmpty()) {
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
        iconOption.ifPresent(route::setIcon);

        trip.getRoutes().add(route);
        trip.setModificationDate(Instant.now());

        tripRepository.saveAndFlush(trip);

        return trip;
    }
}
