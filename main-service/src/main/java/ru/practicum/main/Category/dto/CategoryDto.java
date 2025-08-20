package ru.practicum.main.Category.dto;

import jakarta.persistence.Id;
import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @Id
    @EqualsAndHashCode.Include
    private long id;

    private String name;

}