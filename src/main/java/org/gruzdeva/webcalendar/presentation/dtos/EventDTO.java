package org.gruzdeva.webcalendar.presentation.dtos;

import org.gruzdeva.webcalendar.persistence.models.Event;

import java.time.LocalDate;

public class EventDTO {
    private String title;
    private LocalDate date;

    public EventDTO() {}

    public EventDTO(Event event) {
        title = event.getTitle();
        date = event.getDate();
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }
}
