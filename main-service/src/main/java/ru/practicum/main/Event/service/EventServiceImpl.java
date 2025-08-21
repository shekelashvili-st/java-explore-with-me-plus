package ru.practicum.main.Event.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main.Event.dto.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    // private final EventRepository eventRepository;
    // private final CategoryRepository categoryRepository;
    // private final StatsClient statsClient;

    // ===== Public =====
    @Override
    public List<EventShortDto> getAllPublicEvents(String text, List<Long> categories, Boolean paid, String rangeStart,
                                                String rangeEnd, Boolean onlyAvailable, String sort, Integer from, Integer size) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public EventFullDto getPublishedEventById(Long eventId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ===== Private =====
    @Override
    public List<EventShortDto> getUserEvents(Long userId, Integer from, Integer size) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    @Transactional
    public EventFullDto addEvent(Long userId, NewEventDto body) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public EventFullDto getEventByUser(Long userId, Long eventId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    @Transactional
    public EventFullDto updateUserEvent(Long userId, Long eventId, UpdateEventUserRequest body) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // ===== Admin =====
    @Override
    public List<EventFullDto> searchAdminEvents(List<Long> users, List<String> states, List<Long> categories,
                                                String rangeStart, String rangeEnd, Integer from, Integer size) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    @Transactional
    public EventFullDto updateByAdmin(Long eventId, UpdateEventAdminRequest body) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

