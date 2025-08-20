package ru.practicum.main.Category.dto;

import ru.practicum.main.Category.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryDto dto) {
        return Category.builder()
                .name(dto.getName())
                .build();
    }

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category updateFromDto(CategoryDto dto, Category category) {
        category.setName(dto.getName());
        return category;
    }
}