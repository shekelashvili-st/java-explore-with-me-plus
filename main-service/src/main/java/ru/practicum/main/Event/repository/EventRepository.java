package ru.practicum.main.Event.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import ru.practicum.main.Event.entity.Event;
import ru.practicum.main.Event.entity.EventState;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    Page<Event> findByInitiatorId(Long initiatorId, Pageable pageable);
    Optional<Event> findByIdAndState(Long id, EventState state);

}
