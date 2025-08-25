package ru.practicum.main.Compilation.service;

import ru.practicum.main.Compilation.dto.CompilationDto;
import ru.practicum.main.Compilation.dto.FindCompilationParams;
import ru.practicum.main.Compilation.dto.NewCompilationDto;
import ru.practicum.main.Compilation.dto.UpdateCompilationRequest;

import java.util.List;

public interface CompilationService {
    CompilationDto create(NewCompilationDto dto);

    CompilationDto update(UpdateCompilationRequest dto, Long compId);

    void delete(Long compId);

    CompilationDto findById(Long compId);

    List<CompilationDto> getAll(FindCompilationParams params);
}
