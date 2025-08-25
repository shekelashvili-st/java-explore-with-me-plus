package ru.practicum.main.Compilation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.main.Event.dto.EventShortDto;

import java.util.List;

@Data
@AllArgsConstructor
public class CompilationDto {
    private Long id;
    private boolean pinned;
    private String title;
    private List<EventShortDto> events;
}
