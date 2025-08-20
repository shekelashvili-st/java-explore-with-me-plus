package ru.practicum.main.User.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDto {
    @Id
    @EqualsAndHashCode.Include
    private long id;

    @Email
    private String email;

    @NotBlank
    private String name;
}