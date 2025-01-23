package com.space.travellerserver.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class TripDto {
    Long ownerId;
    String title;
    String description;
    Date firstDay;
    Date lastDay;
}
