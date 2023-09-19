package com.vi5hnu.blogapi.service.impl;

import com.vi5hnu.blogapi.Dto.CategoryDto;
import com.vi5hnu.blogapi.exception.ApiException;
import com.vi5hnu.blogapi.exception.ResourceNotFoundException;
import com.vi5hnu.blogapi.model.Category;
import com.vi5hnu.blogapi.repository.CategoryRepository;
import com.vi5hnu.blogapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    final private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        final Optional<Category> opCategory=this.categoryRepository.findByName(categoryDto.getName());
        if(opCategory.isPresent()){
            categoryDto.setId(opCategory.get().getId());
            return categoryDto;
        }
        Category category=new Category(null, categoryDto.getName(), categoryDto.getDescription(), Collections.emptyList());
        Category savedCategory=this.categoryRepository.save(category);

        categoryDto.setId(savedCategory.getId());
        return categoryDto;
    }

    @Override
    public CategoryDto getCategory(Long id) {
        final Category category=this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("category does not exists for id %s.",id)));
        return new CategoryDto(category.getId(), category.getName(), category.getDescription());
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return this.categoryRepository.findAll().stream().map(category->new CategoryDto(category.getId(), category.getName(), category.getDescription())).toList();
    }

    @Override
    public CategoryDto updateCategory(Long id,CategoryDto categoryDto) {
        final Category exCategroy=this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("category does not exists for id %s.",id)));
        exCategroy.setName(categoryDto.getName());
        exCategroy.setDescription(categoryDto.getDescription());
        this.categoryRepository.save(exCategroy);
        categoryDto.setId(exCategroy.getId());
        return categoryDto;
    }

    @Override
    public void deleteCategory(Long id) {
        if(!this.categoryRepository.existsById(id)){
            throw new ResourceNotFoundException(String.format("category does not exists for id %s", id));
        }
        this.categoryRepository.deleteById(id);
    }
}
