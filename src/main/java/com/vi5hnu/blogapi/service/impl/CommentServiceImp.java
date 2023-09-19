package com.vi5hnu.blogapi.service.impl;

import com.vi5hnu.blogapi.Dto.CommentDto;
import com.vi5hnu.blogapi.exception.ResourceNotFoundException;
import com.vi5hnu.blogapi.model.Comment;
import com.vi5hnu.blogapi.model.Post;
import com.vi5hnu.blogapi.repository.CommentRepository;
import com.vi5hnu.blogapi.repository.PostRepository;
import com.vi5hnu.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImp implements CommentService {
    final private CommentRepository commentRepository;
    final private PostRepository postRepository;

    @Autowired
    CommentServiceImp(CommentRepository commentRepository,PostRepository postRepository){
        this.commentRepository=commentRepository;
        this.postRepository=postRepository;
    }
    @Override
    public CommentDto createComment(UUID id, CommentDto commentDto) {
        final Post exPost=this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("post with id %s does not exists.",id)));
        final Comment comment=new Comment(null,commentDto.getName(), commentDto.getEmail(),commentDto.getBody(),exPost);
        final Comment savedComment=this.commentRepository.save(comment);
        commentDto.setId(comment.getId());
        return commentDto;
    }

    @Override
    public List<CommentDto> getAllComments(UUID id) {
        //return this.commentRepository.findAllById(List.of(id)).stream().map(CommentServiceImp::mapToDto).toList();
        final Post post=this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("comments not found for post id %s",id)));
        return post.getComments().stream().map(CommentServiceImp::mapToDto).toList();
    }

    @Override
    public CommentDto getComment(UUID pid, UUID cid) {
        ////////getComments will return comment object list then we find our own comment on it..
        //final Post post=this.postRepository.findById(pid).orElseThrow(()->new ResourceNotFoundException(String.format("No Post exists for post id %s",pid)));
        //final Comment postComment= post.getComments().stream().filter((comment)->comment.getId().equals(cid)).findFirst().orElseThrow(()->new ResourceNotFoundException(String.format("No Comment with id %s exist for Post with id %s",cid,pid)));
        //return mapToDto(postComment);
        ////////
        final Post post=this.postRepository.findById(pid).orElseThrow(()->new ResourceNotFoundException(String.format("No Post exists for post id %s",pid)));
        final Comment comment=this.commentRepository.findById(cid).orElseThrow(()->new ResourceNotFoundException(String.format("No Comment exists for comment id %s",cid)));
        if(!post.getId().equals(comment.getPost().getId())){
            throw new ResourceNotFoundException(String.format("No Comment with id %s exist for Post with id %s",cid,pid));
        }
        return mapToDto(comment);
    }

    @Override
    public CommentDto getComment(UUID cid) {
        final Comment comment=this.commentRepository.findById(cid).orElseThrow(()->new ResourceNotFoundException(String.format("No Comment exists for comment id %s",cid)));
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(UUID cid, CommentDto commentDto) {
        final Comment comment=this.commentRepository.findById(cid).orElseThrow(()->new ResourceNotFoundException(String.format("No Comment exists for comment id %s",cid)));
        comment.setBody(commentDto.getBody());
        return mapToDto(comment);
    }
    @Override
    public CommentDto updateComment(UUID pid, UUID cid, CommentDto commentDto) {
        final Post post=this.postRepository.findById(pid).orElseThrow(()->new ResourceNotFoundException(String.format("No Post exists for post id %s",pid)));
        final Comment comment=this.commentRepository.findById(cid).orElseThrow(()->new ResourceNotFoundException(String.format("No Comment exists for comment id %s",cid)));
        if(!post.getId().equals(comment.getPost().getId())){
            throw new ResourceNotFoundException(String.format("No Comment with id %s exist for Post with id %s",cid,pid));
        }
        comment.setBody(commentDto.getBody());
        return mapToDto(this.commentRepository.save(comment));
    }

    @Override
    public boolean deleteComment(UUID pid, UUID cid) {
        final Post post=this.postRepository.findById(pid).orElseThrow(()->new ResourceNotFoundException(String.format("No Post exists for post id %s",pid)));
        boolean deleted=post.getComments().removeIf((comment->comment.getId().equals(cid)));
        if(deleted) this.postRepository.save(post);
        return deleted;
    }

    public static CommentDto mapToDto(Comment comment){
        return new CommentDto(comment.getId(),comment.getName(), comment.getEmail(),comment.getBody());
    }
}
