package org.gruzdeva.webcalendar.presentation.controllers;

import org.gruzdeva.webcalendar.presentation.dtos.EventCreatedResponse;
import org.gruzdeva.webcalendar.presentation.dtos.EventCreationBody;
import org.gruzdeva.webcalendar.presentation.dtos.EventDTO;
import org.gruzdeva.webcalendar.services.EventService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /*
    @GetMapping("")
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> allEvents = eventService.getAllEvents();
        HttpStatus resStatus = HttpStatus.OK;
        if (allEvents.isEmpty()) {
            resStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(allEvents, resStatus);
    }
    */

    @GetMapping("")
    public ResponseEntity<List<EventDTO>> getEventInTimePeriod(
            @RequestParam(name="start_time", required = false) Optional<String> startDate,
            @RequestParam(name="end_time", required = false) Optional<String> endDate
    ) {
        List<EventDTO> events;
        HttpStatus resStatus = HttpStatus.OK;
        if (startDate.isPresent() && endDate.isPresent()) {
            events = eventService.getEventsInTimePeriod(LocalDate.parse(startDate.orElse("")), LocalDate.parse(endDate.orElse("")));
        } else {
            events = eventService.getAllEvents();
        }

        if (events.isEmpty()) {
            resStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(events, resStatus);
    }


    @PostMapping("")
    public ResponseEntity<EventCreatedResponse> createEvent(@Valid @RequestBody EventCreationBody eventBody) {
        LocalDate date = LocalDate.parse(eventBody.getDate());
        EventDTO event = eventService.createEvent(eventBody.getTitle(), date);
        EventCreatedResponse response = new EventCreatedResponse("The event has been added!", event.getTitle(),
                event.getDate());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/today")
    public ResponseEntity<List<EventDTO>> getTodayEvents() {
        List<EventDTO> eventsToday = eventService.getEventsByDate(LocalDate.now());
        HttpStatus resStatus = HttpStatus.OK;
        if (eventsToday.isEmpty()) {
            resStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(eventsToday, resStatus);
    }

    @GetMapping("/{id}")
    @Validated
    public ResponseEntity<EventDTO> getEventById(@PathVariable("id") @Positive long id) {
        EventDTO event = eventService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }



}
