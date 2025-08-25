package ru.practicum.main.Request.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.main.Request.entity.ParticipationStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestStatusUpdateRequest {
    private List<Long> requestIds;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ParticipationStatus status;
}
