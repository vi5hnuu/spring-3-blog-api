package com.vi5hnu.blogapi.service.impl;

import com.vi5hnu.blogapi.Dto.BlogPostResponse;
import com.vi5hnu.blogapi.Dto.CommentDto;
import com.vi5hnu.blogapi.Dto.PostDto;
import com.vi5hnu.blogapi.exception.ResourceNotFoundException;
import com.vi5hnu.blogapi.model.Post;
import com.vi5hnu.blogapi.repository.PostRepository;
import com.vi5hnu.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    final private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        final Post post=new Post(null,postDto.getTitle(), postDto.getDescription(), postDto.getContent(), Collections.emptyList());
        final Post savedPost=this.postRepository.save(post);

        postDto.setId(savedPost.getId());
        postDto.setComments(post.getComments().stream().map(CommentServiceImp::mapToDto).toList());
        return postDto;
    }

    @Override
    public BlogPostResponse getAllPosts(int pageSize, int pageNo,String sortBy,String orderBy) {
        Sort sort = switch (orderBy) {
            case "asc" -> Sort.by(sortBy).ascending();
            case "dsc" -> Sort.by(sortBy).descending();
            default -> Sort.by(sortBy);
        };
        final Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        final Page<Post> page=this.postRepository.findAll(pageable);
        final List<PostDto> posts=page.getContent()
                .stream()
                .map((post)-> new PostDto(post.getId(),post.getTitle(),post.getDescription(),post.getContent(),post.getComments().stream().map(CommentServiceImp::mapToDto).toList()))
                .toList();
        return new BlogPostResponse(posts,page.getNumber(),page.getSize(),page.getTotalElements(),page.getTotalPages(),page.isLast());
    }

    @Override
    public PostDto getPostById(Long id) {
        final Post post=this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("post with id %d not found",id)));
        return new PostDto(post.getId(), post.getTitle(), post.getDescription(), post.getContent(),post.getComments().stream().map(CommentServiceImp::mapToDto).toList());
    }

    @Override
    public PostDto updatePostById(Long id, PostDto postDto) {
        final Post exPost=this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("post with id %d not found",id)));
        exPost.setTitle(postDto.getTitle());
        exPost.setDescription(postDto.getDescription());
        exPost.setContent(postDto.getContent());
        //update post
        final Post savedPost=this.postRepository.save(exPost);
        return mapToDto(savedPost);
    }
    @Override
    public void deletePostById(Long id) {
        final Post expost=this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Post with id %d not found.",id)));
        this.postRepository.deleteById(id);
    }

    public static PostDto mapToDto(Post post){
        return new PostDto(post.getId(), post.getTitle(), post.getDescription(), post.getContent(),post.getComments().stream().map(CommentServiceImp::mapToDto).toList());
    }
    public static Post mapToPost(PostDto postDto){
        return new Post(postDto.getId(), postDto.getTitle(), postDto.getDescription(), postDto.getContent(),null);//we dont take comment initially
    }

    @Override
    public long postCount() {
        return this.postRepository.count();
    }
}
