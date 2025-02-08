package com.space.travellerserver.entity.trip;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.Instant;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.space.travellerserver.entity.attendee.Attendee;
import com.space.travellerserver.entity.user.User;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private User owner;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private Instant firstDay;

    @Column(nullable = false)
    private Instant lastDay;

    @Column(nullable = false)
    private Instant creationDate;

    @Column(nullable = false)
    private Instant modificationDate;

    @Column(nullable = false)
    private TripStatus tripStatus;

    @OneToMany(mappedBy = "trip")
    @JsonIgnoreProperties("trip")
    Set<Attendee> attendees;
}
