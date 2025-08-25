package ru.practicum.main.Views.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.main.Views.model.EventView;

public interface EventViewRepository extends JpaRepository<EventView, Long> {
    boolean existsByIpAndEventId(String ip, Long eventId);

    Long countByEventId(Long eventId);
}

