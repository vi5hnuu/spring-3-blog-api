package com.vi5hnu.blogapi.service;

import com.vi5hnu.blogapi.Dto.BlogPostResponse;
import com.vi5hnu.blogapi.Dto.PostDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostDto createPost(PostDto postDto);
    BlogPostResponse getAllPosts(int pageSize, int pageNo,String sortBy,String orderBy);
    PostDto getPostById(UUID id);
    PostDto updatePostById(UUID id, PostDto postDto);
    void deletePostById(UUID id);
    List<PostDto> getAllPostByCategoryId(UUID id);
    long postCount();
}
