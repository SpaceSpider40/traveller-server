package com.space.travellerserver.entity.trip;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;
import java.util.Set;

import com.space.travellerserver.entity.user.User;

@Setter
@Getter
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
    private Date firstDay;

    @Column(nullable = false)
    private Date lastDay;

    @OneToMany(mappedBy = "trip")
    Set<Attedee> attendies;

}
