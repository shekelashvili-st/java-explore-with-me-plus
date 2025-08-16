package ru.practicum.stat.service;

import ru.practicum.stat.dto.EndpointHit;
import ru.practicum.stat.dto.ViewStats;

import java.util.List;

public interface StatService {
    void hit(EndpointHit hit);

    List<ViewStats> getStats(String start, String end, List<String> uris, boolean unique);
}
