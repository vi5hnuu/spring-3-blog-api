package com.vi5hnu.blogapi.repository;

import com.vi5hnu.blogapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment,UUID> {
}
