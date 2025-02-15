package com.space.travellerserver.dto.trip;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.space.travellerserver.util.TimeUtils;

import lombok.Data;

@Data
public class TripAddRouteDto {
    private String title;
    private String description;
    private String iconName;
    private Instant startDate;
    private Instant endDate;
    
    @JsonCreator
    public TripAddRouteDto(
        @JsonProperty String title,
        @JsonProperty String description,
        @JsonProperty String iconName,
        @JsonProperty String zoneId,
        @JsonProperty("startDate") String startDate,
        @JsonProperty("endDate") String endDate
    ) {
        if (!TimeUtils.isZoneIdValid(zoneId)) {
            throw new IllegalArgumentException("Invalid time zone ID");
        }

        this.title = title;
        this.description = description;
        this.iconName = iconName;
        this.startDate = TimeUtils.parseDate(startDate, zoneId);
        this.endDate = TimeUtils.parseDate(endDate, zoneId);
    }
}
