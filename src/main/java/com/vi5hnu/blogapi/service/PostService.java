package com.vi5hnu.blogapi.service;

import com.vi5hnu.blogapi.Dto.BlogPostResponse;
import com.vi5hnu.blogapi.Dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    BlogPostResponse getAllPosts(int pageSize, int pageNo,String sortBy,String orderBy);
    PostDto getPostById(Long id);
    PostDto updatePostById(Long id,PostDto postDto);
    void deletePostById(Long id);
    List<PostDto> getAllPostByCategoryId(Long id);
    long postCount();
}
