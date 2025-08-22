package ru.practicum.main.Event.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewEventDto {
    @NotBlank @Size(min = 20, max = 2000)
    private String annotation;

    @NotNull
    private Long category;

    @NotBlank @Size(min = 20, max = 7000)
    private String description;

    @NotBlank
    private String eventDate;

    @NotNull
    private LocationDto location;

    private Boolean paid = false;

    @Min(0)
    private Integer participantLimit = 0;
    private Boolean requestModeration = true;

    @NotBlank @Size(min = 3, max = 120)
    private String title;
}

