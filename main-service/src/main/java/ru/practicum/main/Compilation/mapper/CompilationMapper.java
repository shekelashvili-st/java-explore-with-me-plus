package ru.practicum.main.Compilation.mapper;

import ru.practicum.main.Compilation.dto.CompilationDto;
import ru.practicum.main.Compilation.dto.NewCompilationDto;
import ru.practicum.main.Compilation.entity.Compilation;
import ru.practicum.main.Event.mapper.EventMapper;

public class CompilationMapper {
    public static Compilation toEntity(NewCompilationDto dto) {
        if (dto == null) {
            return null;
        }
        return new Compilation(null, dto.getTitle(), dto.isPinned(), null, null);
    }

    public static CompilationDto toDto(Compilation entity) {
        if (entity == null) {
            return null;
        }
        return new CompilationDto(entity.getId(),
                entity.isPinned(),
                entity.getTitle(),
                entity.getEvents().stream().map(EventMapper::toShortDto).toList());
    }
}
