package com.vi5hnu.blogapi.repository;

import com.vi5hnu.blogapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
