package ru.practicum.main.Event.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import ru.practicum.main.Event.entity.Event;
import ru.practicum.main.Event.entity.EventState;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    Page<Event> findByInitiatorId(Long initiatorId, Pageable pageable);

    Optional<Event> findByIdAndState(Long id, EventState state);

    @Modifying
    @Query("UPDATE Event e SET e.views = :views WHERE e.id = :eventId")
    void setViews(@Param("eventId") Long eventId, @Param("views") Long views);
}
