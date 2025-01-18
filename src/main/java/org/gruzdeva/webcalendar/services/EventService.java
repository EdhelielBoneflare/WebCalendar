package org.gruzdeva.webcalendar.services;

import org.gruzdeva.webcalendar.persistence.models.Event;
import org.gruzdeva.webcalendar.presentation.dtos.EventDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventService {
    public EventService() {}

    public EventDTO createEvent(String title, LocalDate date) {
        Event event = new Event(title, date);
        return new EventDTO(event);
    }
}
