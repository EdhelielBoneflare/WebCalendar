package org.gruzdeva.webcalendar.presentation.dtos;

import org.gruzdeva.webcalendar.utils.ValidDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EventCreationBody{
    @NotBlank(message = "Title for the event is required.")
    @Size(max = 100, message = "Title must be less than 100 characters")
    String title = "basic title";

    @NotBlank(message = "Date of the event is required.")
    @ValidDate(message = "Date must be in the format yyyy-MM-dd")
    String date = "1990-01-01";

    public EventCreationBody() {}

    public EventCreationBody(@JsonProperty("event") String title, @JsonProperty("date") String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
