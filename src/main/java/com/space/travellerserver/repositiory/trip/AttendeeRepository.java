package com.space.travellerserver.repositiory.trip;

import org.springframework.data.jpa.repository.JpaRepository;

import com.space.travellerserver.entity.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

}
