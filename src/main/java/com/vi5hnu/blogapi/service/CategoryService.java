package com.vi5hnu.blogapi.service;

import com.vi5hnu.blogapi.Dto.CategoryDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategory(UUID id);
    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(UUID id, CategoryDto categoryDto);

    void deleteCategory(UUID id);
}
