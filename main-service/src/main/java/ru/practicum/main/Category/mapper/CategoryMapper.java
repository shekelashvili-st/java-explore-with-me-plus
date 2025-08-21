package ru.practicum.main.Category.mapper;

import ru.practicum.main.Category.dto.CategoryDto;
import ru.practicum.main.Category.dto.NewCategoryDto;
import ru.practicum.main.Category.entity.Category;

public class CategoryMapper {

    public static Category toEntity(NewCategoryDto dto) {
        return new Category(dto.getName());
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