package com.vi5hnu.blogapi.repository;

import com.vi5hnu.blogapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
