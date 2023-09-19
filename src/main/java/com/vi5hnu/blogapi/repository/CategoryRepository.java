package com.vi5hnu.blogapi.repository;

import com.vi5hnu.blogapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category,UUID> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
}
