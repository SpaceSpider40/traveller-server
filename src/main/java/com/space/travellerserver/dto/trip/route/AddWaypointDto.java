package com.space.travellerserver.dto.trip.route;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.space.travellerserver.entity.Icon;

import lombok.Data;

@Data
public class AddWaypointDto {
    private Icon icon;
    private float longitude;
    private float latitude;

    @JsonCreator
    public AddWaypointDto(Icon icon, float longitude, float latitude) {

        if (latitude == 0 || longitude == 0) {
            throw new IllegalArgumentException("Latitude and longitude must be provided");
        }

        this.icon = icon;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
