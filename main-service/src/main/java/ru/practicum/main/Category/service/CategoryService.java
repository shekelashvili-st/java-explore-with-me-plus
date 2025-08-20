package ru.practicum.main.Category.service;


import ru.practicum.main.Category.dto.CategoryDto;
import ru.practicum.main.Category.dto.NewCategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(NewCategoryDto newCategoryDto);

    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);

    void deleteCategory(Long categoryId);

    CategoryDto getCategoryById(Long categoryId);

    List<CategoryDto> getAllCategories(Integer from, Integer size);

    List<CategoryDto> getCategoriesByIds(List<Long> ids);

}