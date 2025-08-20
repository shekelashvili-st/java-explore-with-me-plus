package ru.practicum.main.Category.service;

import ru.practicum.main.Category.dto.CategoryDto;
import ru.practicum.main.Category.dto.NewCategoryDto;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public CategoryDto createCategory(NewCategoryDto newCategoryDto) {
        return null;
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void deleteCategory(Long categoryId) {

    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories(Integer from, Integer size) {
        return List.of();
    }

    @Override
    public List<CategoryDto> getCategoriesByIds(List<Long> ids) {
        return List.of();
    }
}