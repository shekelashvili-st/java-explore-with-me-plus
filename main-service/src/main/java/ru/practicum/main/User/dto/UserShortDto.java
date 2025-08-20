package ru.practicum.main.User.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserShortDto {
    @Id
    @EqualsAndHashCode.Include
    private long id;

    @NotBlank
    private String name;
}
