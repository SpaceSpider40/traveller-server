package com.space.travellerserver.entity.attendee;

import com.space.travellerserver.entity.trip.Trip;
import com.space.travellerserver.entity.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendee {
    @Id
    @Column(unique=true, nullable=false)
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_trip", nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private AttendeeStatus attendeeStatus;

}
