package com.space.travellerserver.repositiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.space.travellerserver.entity.Icon;
import java.util.Optional;


public interface IconRepository extends JpaRepository<Icon, Long> {
    Optional<Icon> findByName(String name);
}
