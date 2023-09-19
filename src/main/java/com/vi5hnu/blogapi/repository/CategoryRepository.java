package com.vi5hnu.blogapi.repository;

import com.vi5hnu.blogapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
}
