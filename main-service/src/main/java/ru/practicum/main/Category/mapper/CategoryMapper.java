package ru.practicum.main.Category.mapper;

import ru.practicum.main.Category.dto.CategoryDto;
import ru.practicum.main.Category.dto.NewCategoryDto;
import ru.practicum.main.Category.entity.Category;

public class CategoryMapper {
    public static Category toEntity(NewCategoryDto dto) {
        return new Category(null, dto.name());
    }

    public static CategoryDto toDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}