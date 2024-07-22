package com.space.travellerserver.repositiory.trip;

import com.space.travellerserver.entity.trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Trip, Integer> {
    
}
