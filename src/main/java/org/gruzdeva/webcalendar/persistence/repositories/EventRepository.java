package org.gruzdeva.webcalendar.persistence.repositories;

import org.gruzdeva.webcalendar.persistence.models.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDate(LocalDate date);

    List<Event> findEventsByDateBetween(LocalDate dateAfter, LocalDate dateBefore);
}
