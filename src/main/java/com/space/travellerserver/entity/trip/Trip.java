package com.space.travellerserver.entity.trip;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    // @Column(nullable = false)
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_owner", nullable = false)
    @JsonIgnoreProperties("trips")
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

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("trip")
    final List<Attendee> attendees = new ArrayList<>();
}
