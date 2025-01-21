package org.gruzdeva.webcalendar.presentation.controllers;

import org.gruzdeva.webcalendar.presentation.dtos.EventCreatedResponse;
import org.gruzdeva.webcalendar.presentation.dtos.EventCreationBody;
import org.gruzdeva.webcalendar.presentation.dtos.EventDTO;
import org.gruzdeva.webcalendar.services.EventService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/today")
    public ResponseEntity<?> getTodayEvents() {
        List<EventDTO> eventsToday = eventService.getEventsByDate(LocalDate.now());
        HttpStatus resStatus = HttpStatus.OK;
        if (eventsToday.isEmpty()) {
            resStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(eventsToday, resStatus);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllEvents() {
        List<EventDTO> allEvents = eventService.getAllEvents();
        HttpStatus resStatus = HttpStatus.OK;
        if (allEvents.isEmpty()) {
            resStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(allEvents, resStatus);
    }

    @PostMapping("")
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventCreationBody eventBody) {
        LocalDate date = LocalDate.parse(eventBody.getDate());
        EventDTO event = eventService.createEvent(eventBody.getTitle(), date);
        EventCreatedResponse response = new EventCreatedResponse("The event has been added!", event.getTitle(),
                event.getDate());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
