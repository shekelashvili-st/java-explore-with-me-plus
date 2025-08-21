package ru.practicum.main.Event.service;

import ru.practicum.main.Event.dto.*;

import java.util.List;

public interface EventService {
    // ===== Public =====
    List<EventShortDto> getAllPublicEvents(String text, List<Long> categories,
                                         Boolean paid, String rangeStart, String rangeEnd,
                                         Boolean onlyAvailable, String sort, Integer from, Integer size);
    EventFullDto getPublishedEventById(Long eventId);

    // ===== Private =====
    List<EventShortDto> getUserEvents(Long userId, Integer from, Integer size);
    EventFullDto addEvent(Long userId, NewEventDto body);
    EventFullDto getEventByUser(Long userId, Long eventId);
    EventFullDto updateUserEvent(Long userId, Long eventId, UpdateEventUserRequest body);

    // ===== Admin =====
    List<EventFullDto> searchAdminEvents(List<Long> users, List<String> states, List<Long> categories,
                                         String rangeStart, String rangeEnd, Integer from, Integer size);
    EventFullDto updateByAdmin(Long eventId, UpdateEventAdminRequest body);
}

