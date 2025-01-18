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

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController() {
        eventService = new EventService();
    }

    @GetMapping("/today")
    public ResponseEntity<?> getTodayEvents() {
        return new ResponseEntity<>(new String[] {}, HttpStatus.OK);
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
