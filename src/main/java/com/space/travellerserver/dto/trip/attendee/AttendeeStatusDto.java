package com.space.travellerserver.dto.trip.attendee;

import com.space.travellerserver.entity.attendee.AttendeeStatus;

import lombok.Data;

@Data
public class AttendeeStatusDto {
    private AttendeeStatus status;
}
