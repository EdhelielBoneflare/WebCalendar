package org.gruzdeva.webcalendar.presentation.controllers;

import org.gruzdeva.webcalendar.presentation.dtos.EventSavedResponse;
import org.gruzdeva.webcalendar.presentation.dtos.EventSaveBody;
import org.gruzdeva.webcalendar.presentation.dtos.EventDTO;
import org.gruzdeva.webcalendar.services.EventService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.ValidationException;
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
    public ResponseEntity<EventSavedResponse> createEvent(@Valid @RequestBody EventSaveBody eventBody) {
        if (eventBody.getTitle() == null || eventBody.getTitle().trim().isEmpty()) {
            throw new ValidationException("Title for the event is required.");
        }
        if (eventBody.getDate() == null || eventBody.getDate().trim().isEmpty()) {
            throw new ValidationException("Date of the event is required.");
        }
        LocalDate date = LocalDate.parse(eventBody.getDate());
        EventDTO event = eventService.createEvent(eventBody.getTitle(), date);
        EventSavedResponse response = new EventSavedResponse("The event has been added!", event.getTitle(),
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

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<EventSavedResponse> updateEvent(
            @PathVariable("id") @Positive long id,
            @Valid @RequestBody EventSaveBody eventBody)
    {
        LocalDate date = !(eventBody.getDate() == null || eventBody.getDate().isBlank()) ? LocalDate.parse(eventBody.getDate()) : null;
        EventDTO event = eventService.updateEvent(id, eventBody.getTitle(), date);
        EventSavedResponse response = new EventSavedResponse("The event has been updated!", event.getTitle(),
                event.getDate());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Validated
    public ResponseEntity<EventDTO> deleteEvent(@PathVariable("id") @Positive long id) {
        EventDTO event = eventService.deleteEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

}
