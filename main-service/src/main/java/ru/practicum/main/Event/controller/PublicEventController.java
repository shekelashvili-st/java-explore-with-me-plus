package ru.practicum.main.Event.controller;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.Event.dto.EventFullDto;
import ru.practicum.main.Event.dto.EventShortDto;
import ru.practicum.main.Event.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PublicEventController {

    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> getAllPublicEvents(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @Pattern(regexp = "EVENT_DATE|VIEWS", message = "sort must be EVENT_DATE or VIEWS")
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        log.info("PUBLIC /events from={} size={}", from, size);
        List<EventShortDto> newList = eventService.getAllPublicEvents(text, categories, paid,
                rangeStart, rangeEnd, onlyAvailable, sort, from, size);
        log.debug("SUCCESS: PUBLIC /events newList={}", newList);
        return newList;
    }

    @GetMapping("/{id}")
    public EventFullDto getPublishedEventById(@PathVariable Long id) {
        log.info("PUBLIC /events/{}", id);
        EventFullDto newdto = eventService.getPublishedEventById(id);
        log.debug("SUCCESS: PUBLIC /events/{} newdto={}", id, newdto);
        return newdto;
    }
}

