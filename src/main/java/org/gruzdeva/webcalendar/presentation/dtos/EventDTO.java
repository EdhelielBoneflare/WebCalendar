package org.gruzdeva.webcalendar.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.gruzdeva.webcalendar.persistence.models.Event;

import java.time.LocalDate;

@JsonPropertyOrder({"id", "event", "date"})
public class EventDTO {
    private final long id;
    @JsonProperty("event")
    private final String title;
    private final LocalDate date;

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
