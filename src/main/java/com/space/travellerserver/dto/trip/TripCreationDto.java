package com.space.travellerserver.dto.trip;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TripCreationDto {
    Long ownerId;
    String title;
    String description;
    Instant firstDay;
    Instant lastDay;

    @JsonCreator
    public TripCreationDto(Long ownerId, String title, String description, String zoneId, @JsonProperty("firstDay") String firstDay, @JsonProperty("lastDay") String lastDay) {
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;

        if (!isZoneIdValid(zoneId)) {
            throw new IllegalArgumentException("Invalid time zone ID");
        }

        this.firstDay = parseDate(firstDay, zoneId);
        this.lastDay = parseDate(lastDay, zoneId);
    }

    private boolean isZoneIdValid(String zoneId) {
        return zoneId != null && ZoneId.getAvailableZoneIds().contains(zoneId);
    }

    private Instant parseDate(String date, String timeZoneName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss'Z'");
        return LocalDateTime.parse(
            date,
            formatter
        )
        .atZone(
            ZoneId.of(timeZoneName)
        )
        .toInstant();
    }
}
