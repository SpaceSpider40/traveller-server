package com.space.travellerserver.tasks;

import java.time.Instant;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.AllArgsConstructor;

@EnableAsync
@EnableScheduling
@AllArgsConstructor
public class TripTasks {

    private final TaskScheduler scheduler;

    public void scheduleTripBegin(Instant instant) {
        ScheduledFuture<?> future = scheduler.schedule(
                () -> System.out.println("Trip has begun!"), instant);

        
    }
}
