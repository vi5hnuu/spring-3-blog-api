package com.vi5hnu.blogapi.repository;

import com.vi5hnu.blogapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post,UUID> {
    List<Post> findAllByCategoryId(UUID id);
}
