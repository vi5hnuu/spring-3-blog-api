package com.vi5hnu.blogapi.controller;

import com.vi5hnu.blogapi.Dto.CategoryDto;
import com.vi5hnu.blogapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {
    final private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @PostMapping(path = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(this.categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id){
        return new ResponseEntity<>(this.categoryService.getCategory(id), HttpStatus.OK);
    }
    @GetMapping(path = "")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }
    @PutMapping(path = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(this.categoryService.updateCategory(id,categoryDto), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>("category deleted successfully.", HttpStatus.OK);
    }
}
