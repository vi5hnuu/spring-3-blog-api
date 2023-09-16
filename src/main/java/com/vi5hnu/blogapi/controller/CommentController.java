package com.vi5hnu.blogapi.controller;

import com.vi5hnu.blogapi.Dto.CommentDto;
import com.vi5hnu.blogapi.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/posts/{postId}")
public class CommentController {
    final private CommentService commentService;

    @Autowired
    CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @PostMapping(path = "comments")
    ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId")Long id,@Valid @RequestBody(required = true) CommentDto commentDto){
        return new ResponseEntity<>(this.commentService.createComment(id,commentDto), HttpStatus.CREATED);
    }
    @GetMapping(path = "comments")
    ResponseEntity<List<CommentDto>> getAllComments(@PathVariable(name = "postId")Long id){
        return new ResponseEntity<>(this.commentService.getAllComments(id), HttpStatus.OK);
    }

    @GetMapping(path = "comments/{id}")
    ResponseEntity<CommentDto> getComment(@PathVariable(name = "postId")Long pid,@PathVariable(name = "id")Long cid){
        return new ResponseEntity<>(this.commentService.getComment(pid,cid), HttpStatus.OK);
//        return new ResponseEntity<>(this.commentService.getComment(cid), HttpStatus.OK);
    }

    @PatchMapping(path = "comments/{id}")
    ResponseEntity<CommentDto> updateComment(@PathVariable(name = "postId")Long pid,@PathVariable(name = "id")Long cid,@Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(this.commentService.updateComment(pid,cid,commentDto),HttpStatus.OK);
    }

    @DeleteMapping(path = "comments/{id}")
    ResponseEntity<String> deleteComment(@PathVariable(name = "postId")Long pid,@PathVariable(name = "id")Long cid){
        return new ResponseEntity<>(String.format("comment for post with post id %s and comment id %s is %s deleted",pid,cid,this.commentService.deleteComment(pid,cid) ? "" : "not"),HttpStatus.OK);
    }
}
