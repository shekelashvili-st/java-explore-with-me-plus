package ru.practicum.main.Request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main.Event.entity.Event;
import ru.practicum.main.Event.entity.EventState;
import ru.practicum.main.Event.repository.EventRepository;
import ru.practicum.main.Exception.ConflictException;
import ru.practicum.main.Exception.ForbiddenException;
import ru.practicum.main.Exception.NotFoundException;
import ru.practicum.main.Request.dto.EventRequestStatusUpdateRequest;
import ru.practicum.main.Request.dto.EventRequestStatusUpdateResult;
import ru.practicum.main.Request.dto.ParticipationRequestDto;
import ru.practicum.main.Request.entity.ParticipationRequest;
import ru.practicum.main.Request.entity.RequestStatus;
import ru.practicum.main.Request.mapper.RequestMapper;
import ru.practicum.main.Request.repository.ParticipationRequestRepository;
import ru.practicum.main.User.Repository.UserRepository;
import ru.practicum.main.User.dto.UserDto;
import ru.practicum.main.User.mapper.UserMapper;
import ru.practicum.main.User.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RequestServiceImpl implements RequestService {

    private final ParticipationRequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    // ----- user side -----
    @Override
    public List<ParticipationRequestDto> getUserRequests(Long userId) {
        UserDto user = userService.getUserById(userId);
        return requestRepository.findByRequesterId(user.getId()).stream()
                .map(RequestMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ParticipationRequestDto addRequest(Long userId, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event with id=" + eventId + " was not found"));

        if (Objects.equals(event.getInitiatorId(), userId)) {
            throw new ConflictException("Initiator cannot request to participate in his own event");
        }
        if (event.getState() != EventState.PUBLISHED) {
            throw new ConflictException("Event must be published");
        }
        if (requestRepository.existsByEventIdAndRequesterId(eventId, userId)) {
            throw new ConflictException("Duplicate request");
        }

        long confirmed = requestRepository.countByEventIdAndStatus(eventId, RequestStatus.CONFIRMED);
        if (event.getParticipantLimit() != null && event.getParticipantLimit() > 0
                && confirmed >= event.getParticipantLimit()) {
            throw new ConflictException("The participant limit has been reached");
        }

        RequestStatus status = RequestStatus.PENDING;
        if ((event.getParticipantLimit() != null && event.getParticipantLimit() == 0)
                || Boolean.FALSE.equals(event.getRequestModeration())) {
            status = RequestStatus.CONFIRMED;
        }

        ParticipationRequest saved = requestRepository.save(
                ParticipationRequest.builder()
                        .eventId(eventId)
                        .requesterId(userId)
                        .status(status)
                        .created(LocalDateTime.now())
                        .build()
        );
        return RequestMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ParticipationRequestDto cancelRequest(Long userId, Long requestId) {
        ParticipationRequest r = requestRepository.findByIdAndRequesterId(requestId, userId)
                .orElseThrow(() -> new NotFoundException("Request with id=" + requestId + " was not found"));

        UserDto user = UserMapper.toUserDto(userService.getEntityById(r.getRequesterId()));
        if (user.getId() != userId) {
            throw new IllegalStateException("You can't cancel request of another user");
        }

        r.setStatus(RequestStatus.CANCELED);
        return RequestMapper.toDto(requestRepository.save(r));
    }

    // ----- event owner side -----
    @Override
    public List<ParticipationRequestDto> getEventRequestsByUserId(Long userId, Long eventId) {
        UserDto u = userService.getUserById(userId);
        Event e = mustBeOwner(u.getId(), eventId);
        return requestRepository.findByEventId(e.getId()).stream()
                .map(RequestMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public EventRequestStatusUpdateResult changeRequestStatus(Long userId, Long eventId,
                                                              EventRequestStatusUpdateRequest body) {
        UserDto u = userService.getUserById(userId);
        Event e = mustBeOwner(u.getId(), eventId);
        if (!e.getInitiatorId().equals(u.getId()))
            throw new ForbiddenException(
                    "User with id = " + userId + " is not a initiator of event with id = " + eventId);

        if (body.getRequestIds() == null || body.getRequestIds().isEmpty()) {
            return new EventRequestStatusUpdateResult(List.of(), List.of());
        }

        RequestStatus target;
        try {
            target = RequestStatus.valueOf(body.getStatus());
        } catch (Exception ex) {
            throw new ConflictException("Unknown status: " + body.getStatus());
        }
        if (target != RequestStatus.CONFIRMED && target != RequestStatus.REJECTED) {
            throw new ConflictException("Unknown status: " + body.getStatus());
        }

        List<ParticipationRequest> requests = requestRepository.findByIdInAndEventId(body.getRequestIds(), eventId);
        if (requests.size() != body.getRequestIds().size()
                || requests.stream().anyMatch(r -> r.getStatus() != RequestStatus.PENDING)) {
            throw new ConflictException("Request must have status PENDING");
        }

        long confirmed = requestRepository.countByEventIdAndStatus(eventId, RequestStatus.CONFIRMED);
        int limit = Optional.ofNullable(e.getParticipantLimit()).orElse(0);

        List<ParticipationRequest> confirmedList = new ArrayList<>();
        List<ParticipationRequest> rejectedList  = new ArrayList<>();

        if (target == RequestStatus.CONFIRMED) {
            for (ParticipationRequest r : requests) {
                if (limit > 0 && confirmed >= limit) {
                    throw new ConflictException("The participant limit has been reached");
                }
                r.setStatus(RequestStatus.CONFIRMED);
                confirmed++;
                confirmedList.add(r);
            }

            if (limit > 0 && confirmed >= limit) {
                requestRepository.rejectAllPendingForEvent(eventId, RequestStatus.REJECTED);
            }
        } else {
            for (ParticipationRequest r : requests) {
                r.setStatus(RequestStatus.REJECTED);
                rejectedList.add(r);
            }
        }

        requestRepository.saveAll(requests);

        return EventRequestStatusUpdateResult.builder()
                .confirmedRequests(confirmedList.stream().map(RequestMapper::toDto).toList())
                .rejectedRequests(rejectedList.stream().map(RequestMapper::toDto).toList())
                .build();
    }

    private Event mustBeOwner(Long userId, Long eventId) {
        Event e = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event with id=" + eventId + " was not found"));
        if (!Objects.equals(e.getInitiatorId(), userId)) {
            throw new NotFoundException("Event with id=" + eventId + " was not found");
        }
        return e;
    }
}

