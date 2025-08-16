package ru.practicum.stat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.practicum.stat.annotations.ValidIp;
import ru.practicum.stat.annotations.ValidUri;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EndpointHit {
    @EqualsAndHashCode.Include
    private long id;

    @NotBlank
    private final String app;

    @ValidUri
    private final String uri;

    @ValidIp
    private final String ip;

    private LocalDateTime timestamp;

    public EndpointHit(String app, String uri, String ip, LocalDateTime timestamp) {
        this.app = app;
        this.uri = uri;
        this.ip = ip;
        this.timestamp = timestamp;
    }
}
