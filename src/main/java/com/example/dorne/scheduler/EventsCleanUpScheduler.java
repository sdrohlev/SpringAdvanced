package com.example.dorne.scheduler;


import com.example.dorne.service.EventService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventsCleanUpScheduler {

    private final EventService eventService;

    public EventsCleanUpScheduler(EventService eventService) {
        this.eventService = eventService;
    }

    //@Scheduled(cron = "00 59 23 * * *")
    @Scheduled(cron = "59 59 23 * * *")
    public void CleanUp() {

        System.out.println("Checking for expired event!");
        this.eventService.expiredEventsCleanUp();
    }

}
