package com.space.travellerserver.service.trip;

import com.space.travellerserver.dto.trip.TripAddAttendeeDto;
import com.space.travellerserver.dto.trip.attendee.AttendeeStatusDto;
import com.space.travellerserver.entity.attendee.Attendee;
import com.space.travellerserver.entity.attendee.AttendeeStatus;
import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.entity.user.User;
import com.space.travellerserver.repositiory.UserRepository;
import com.space.travellerserver.repositiory.trip.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttendeeService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public Trip addAttendee(Long tripId, TripAddAttendeeDto dto) {

        Optional<User> userOption = userRepository.findById(dto.userId);

        if (userOption.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOption.get();
        Trip trip = getTrip(tripId);

        if (Objects.equals(user.getId(), trip.getOwner().getId())) {
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
        trip.setModificationDate(Instant.now());

        tripRepository.saveAndFlush(trip);

        return trip;
    }

    public Attendee changeStatus(Long tripId, Long attendeeId, AttendeeStatusDto dto) {
        Trip trip = getTrip(tripId);

        Attendee attendee = getAttendee(attendeeId, trip);
        attendee.setAttendeeStatus(dto.getStatus());

        tripRepository.saveAndFlush(trip);

        return attendee;
    }

    public Boolean removeAttendee(Long tripId, Long attendeeId) {
        Trip trip = getTrip(tripId);
        Attendee attendee = getAttendee(attendeeId, trip);

        trip.getAttendees().remove(attendee);
        tripRepository.saveAndFlush(trip);

        return true;
    }

    private Attendee getAttendee(Long attendeeId, Trip trip) {
        Optional<Attendee> attendeeOptional = trip.getAttendees().stream().filter(a -> a.getId().equals(attendeeId))
                .findFirst();

        if (attendeeOptional.isEmpty()) {
            throw new IllegalArgumentException("Attendee not found");
        }

        Attendee attendee = attendeeOptional.get();
        return attendee;
    }

    private Trip getTrip(Long tripId) {
        Optional<Trip> tripOptional = tripRepository.findById(tripId);

        if (tripOptional.isEmpty()) {
            throw new IllegalArgumentException("Trip not found");
        }

        return tripOptional.get();
    }
}
