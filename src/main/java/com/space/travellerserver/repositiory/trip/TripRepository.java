package com.space.travellerserver.repositiory.trip;

import com.space.travellerserver.entity.trip.Trip;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import com.space.travellerserver.entity.attendee.Attendee;


public interface TripRepository extends JpaRepository<Trip, Long> {
    @NonNull
    Optional<Trip> findById(@NonNull Long id);

    List<Trip> findByAttendeesContaining(Attendee attendee);
}
