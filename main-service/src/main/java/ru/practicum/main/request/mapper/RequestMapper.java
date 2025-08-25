package ru.practicum.main.request.mapper;

import ru.practicum.main.request.dto.ParticipationRequestDto;
import ru.practicum.main.request.entity.ParticipationRequest;

public class RequestMapper {
    public static ParticipationRequestDto toDto(ParticipationRequest e) {
        return ParticipationRequestDto.builder()
                .id(e.getId())
                .created(e.getCreated())
                .event(e.getEventId())
                .requester(e.getRequesterId())
                .status(e.getStatus())
                .build();
    }
}
