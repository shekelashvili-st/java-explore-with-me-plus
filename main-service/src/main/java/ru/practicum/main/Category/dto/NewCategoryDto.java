package ru.practicum.main.Category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record NewCategoryDto(
        @NotBlank @Size(min = 1, max = 50) String name
) {}