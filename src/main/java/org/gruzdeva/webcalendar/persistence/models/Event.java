package org.gruzdeva.webcalendar.persistence.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="event", nullable = false, columnDefinition = "VARCHAR(200)")

    private String title;
    @Column(name="date", nullable = false)
    @Temporal(TemporalType.DATE)
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

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }
}
