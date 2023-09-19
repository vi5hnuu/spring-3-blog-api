package com.vi5hnu.blogapi.controller;

import com.vi5hnu.blogapi.Dto.BlogPostResponse;
import com.vi5hnu.blogapi.Dto.PostDto;
import com.vi5hnu.blogapi.service.PostService;
import com.vi5hnu.blogapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/posts")
public class PostController {
    final private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        final PostDto post=this.postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @GetMapping(path = "")
    public ResponseEntity<BlogPostResponse> getAllPosts(
            @RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(name = "pageNo",defaultValue = AppConstants.PAGE_NO,required = false) int pageNo,
            @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(name = "orderBy",defaultValue = AppConstants.ORDER_BY,required = false) String orderBy){
        return new ResponseEntity<>(this.postService.getAllPosts(pageSize,pageNo,sortBy,orderBy),HttpStatus.OK);
    }
    @GetMapping(path = "category/{id}")
    public ResponseEntity<List<PostDto>> getAllPostInCategory(@UUID(allowNil = false,message = "invalid category id") @PathVariable(name = "id") java.util.UUID id){
        return new ResponseEntity<>(this.postService.getAllPostByCategoryId(id),HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PostDto> getPost(@UUID(allowNil = false,message = "invalid post id") @PathVariable(name = "id") java.util.UUID id){
        return new ResponseEntity<>(this.postService.getPostById(id),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "{id}")
    public ResponseEntity<PostDto> updatePost(
            @UUID(allowNil = false,message = "invalid post id") @PathVariable(name = "id") java.util.UUID id,
            @Valid @RequestBody(required = true) PostDto postDto){
        return new ResponseEntity<>(this.postService.updatePostById(id,postDto),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deletePost(@UUID(allowNil = false,message = "invalid post id") @PathVariable(name = "id") java.util.UUID id){
        this.postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted.",HttpStatus.OK);
    }
}
