package com.space.travellerserver.dto.trip;

import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.space.travellerserver.util.TimeUtils;

import lombok.Data;

@Data
public class TripCreationDto {
    private Long ownerId;
    private String title;
    private String description;
    private Instant firstDay;
    private Instant lastDay;

    @JsonCreator
    public TripCreationDto(Long ownerId, String title, String description, String zoneId, @JsonProperty("firstDay") String firstDay, @JsonProperty("lastDay") String lastDay) {

        if (!TimeUtils.isZoneIdValid(zoneId)) {
            throw new IllegalArgumentException("Invalid time zone ID");
        }

        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
        this.firstDay = TimeUtils.parseDate(firstDay, zoneId);
        this.lastDay = TimeUtils.parseDate(lastDay, zoneId);
    }
}
