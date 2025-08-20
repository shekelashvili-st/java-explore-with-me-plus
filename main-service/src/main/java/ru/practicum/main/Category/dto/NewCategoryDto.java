package ru.practicum.main.Category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCategoryDto {

    @NotBlank(message = "Имя категории не может быть пустым")
    @Size(min = 1, max = 50, message = "Имя категории должно быть от 1 до 50 знаков")
    private String name;
}