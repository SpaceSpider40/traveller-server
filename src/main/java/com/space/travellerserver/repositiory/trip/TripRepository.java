package com.space.travellerserver.repositiory.trip;

import com.space.travellerserver.entity.trip.Trip;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @NonNull
    Optional<Trip> findById(@NonNull Long id);
}
