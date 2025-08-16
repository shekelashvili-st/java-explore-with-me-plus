package ru.practicum.stat.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.stat.dto.EndpointHit;
import ru.practicum.stat.dto.ViewStats;
import ru.practicum.stat.storage.StatStorage;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class StatServiceImpl implements StatService{
    private final StatStorage statStorage;

    @Override
    public void hit(EndpointHit hit) {
        statStorage.saveHit(hit);
    }

    @Override
    public List<ViewStats> getStats(String start, String end, List<String> uris, boolean unique) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(
                URLDecoder.decode(start, StandardCharsets.UTF_8),
                formatter
        );
        LocalDateTime endDate = LocalDateTime.parse(
                URLDecoder.decode(end, StandardCharsets.UTF_8),
                formatter
        );

        return statStorage.getStats(startDate, endDate, uris, unique);
    }
}
