package org.gruzdeva.webcalendar.persistence.models;

import java.time.LocalDate;

public class Event {

    private String title;
    private LocalDate date;

    public Event() {}

    public Event(String title, String date) {
        this.title = title;
        this.date = LocalDate.parse(date);
    }

    public Event(String title, LocalDate date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }
}
