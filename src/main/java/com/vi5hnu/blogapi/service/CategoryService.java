package com.vi5hnu.blogapi.service;

import com.vi5hnu.blogapi.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategory(Long id);
    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(Long id,CategoryDto categoryDto);

    void deleteCategory(Long id);
}
