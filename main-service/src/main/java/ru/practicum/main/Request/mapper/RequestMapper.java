package ru.practicum.main.Request.mapper;

import ru.practicum.main.Request.dto.ParticipationRequestDto;
import ru.practicum.main.Request.entity.ParticipationRequest;

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
