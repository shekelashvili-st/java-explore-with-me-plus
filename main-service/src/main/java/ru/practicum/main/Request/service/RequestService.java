package ru.practicum.main.Request.service;

import ru.practicum.main.Request.dto.*;

import java.util.List;

public interface RequestService {
    List<ParticipationRequestDto> getUserRequests(Long userId);
    ParticipationRequestDto addRequest(Long userId, Long eventId);
    ParticipationRequestDto cancelRequest(Long userId, Long requestId);

    List<ParticipationRequestDto> getEventRequestsByUserId(Long userId, Long eventId);
    EventRequestStatusUpdateResult changeRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest body);
}
