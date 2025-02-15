package com.space.travellerserver.service.trip;

import com.space.travellerserver.dto.trip.TripAddAttendeeDto;
import com.space.travellerserver.entity.attendee.Attendee;
import com.space.travellerserver.entity.attendee.AttendeeStatus;
import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.entity.user.User;
import com.space.travellerserver.repositiory.UserRepository;
import com.space.travellerserver.repositiory.trip.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttendeeService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    public Trip addAttendee(Long tripId, TripAddAttendeeDto dto){

        Optional<User> userOption = userRepository.findById(dto.userId);
        Optional<Trip> tripOption = tripRepository.findById(tripId);

        if(userOption.isEmpty() || tripOption.isEmpty()){
            throw new IllegalArgumentException("User or Trip not found");
        }

        User user = userOption.get();
        Trip trip = tripOption.get();

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

        tripRepository.saveAndFlush(trip);

        return trip;
    }
}
