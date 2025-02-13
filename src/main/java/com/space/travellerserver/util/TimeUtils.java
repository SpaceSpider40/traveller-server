package com.space.travellerserver.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class TimeUtils {
    public boolean isZoneIdValid(String zoneId) {
        return zoneId != null && ZoneId.getAvailableZoneIds().contains(zoneId);
    }

    public Instant parseDate(String date, String timeZoneName) {
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
