package org.gruzdeva.webcalendar.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;

@JsonPropertyOrder({"message", "event", "date"})
public class EventSavedResponse {
    public String message;
    @JsonProperty("event")
    public String eventTitle;
    @JsonProperty("date")
    public LocalDate eventDate;

    public EventSavedResponse(String message, String title, LocalDate date) {
        this.message = message;
        eventTitle = title;
        eventDate = date;
    }
}
