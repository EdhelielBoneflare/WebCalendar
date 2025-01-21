package org.gruzdeva.webcalendar.presentation.dtos;

import org.gruzdeva.webcalendar.persistence.models.Event;

import java.time.LocalDate;

public class EventDTO {
    private final long id;
    private String title;
    private LocalDate date;

    public EventDTO(Event event) {
        id = event.getId();
        title = event.getTitle();
        date = event.getDate();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }
}
