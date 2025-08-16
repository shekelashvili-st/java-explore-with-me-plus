package ru.practicum.stat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
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
    private final String api;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;
}
