package org.gruzdeva.webcalendar.services;

import org.gruzdeva.webcalendar.persistence.models.Event;
import org.gruzdeva.webcalendar.persistence.repositories.EventRepository;
import org.gruzdeva.webcalendar.presentation.dtos.EventDTO;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(EventDTO::new)
                .toList();
    }

    public List<EventDTO> getEventsByDate(LocalDate date) {
        return eventRepository.findByDate(date).stream()
                .map(EventDTO::new)
                .toList();
    }

    public List<EventDTO> getEventsInTimePeriod(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findEventsByDateBetween(
                startDate.minusDays(1),
                endDate.plusDays(1)
                ).stream()
                .map(EventDTO::new)
                .toList();
    }

    public EventDTO getEventById(Long id) throws NoSuchElementException {
        return eventRepository.findById(id)
                .map(EventDTO::new)
                .orElseThrow(() -> new NoSuchElementException("The event doesn't exist!"));
    }

    @Transactional
    public EventDTO createEvent(String title, LocalDate date) {
        Event event = new Event(title, date);
        Event savedEvent = eventRepository.save(event);
        return new EventDTO(savedEvent);
    }

    @Transactional
    public EventDTO deleteEventById(long id) {
        EventDTO deletedEvent = getEventById(id);
        eventRepository.deleteById(id);
        return deletedEvent;
    }
}
