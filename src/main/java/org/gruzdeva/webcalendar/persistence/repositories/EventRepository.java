package org.gruzdeva.webcalendar.persistence.repositories;

import org.gruzdeva.webcalendar.persistence.models.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDate(LocalDate date);

    List<Event> findEventsByDateBetween(LocalDate dateAfter, LocalDate dateBefore);
}
