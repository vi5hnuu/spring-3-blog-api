package com.vi5hnu.blogapi.controller;

import com.vi5hnu.blogapi.Dto.CommentDto;
import com.vi5hnu.blogapi.service.CommentService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
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
    ResponseEntity<CommentDto> createComment(@UUID(allowNil = false,message = "invalid post id") @PathVariable(name = "postId") java.util.UUID id, @Valid @RequestBody(required = true) CommentDto commentDto){
        return new ResponseEntity<>(this.commentService.createComment(id,commentDto), HttpStatus.CREATED);
    }
    @GetMapping(path = "comments")
    ResponseEntity<List<CommentDto>> getAllComments(@UUID(allowNil = false,message = "invalid post id") @PathVariable(name = "postId")java.util.UUID id){
        return new ResponseEntity<>(this.commentService.getAllComments(id), HttpStatus.OK);
    }

    @GetMapping(path = "comments/{id}")
    ResponseEntity<CommentDto> getComment(@UUID(allowNil = false,message = "invalid post id") @PathVariable(name = "postId")java.util.UUID pid,
                                          @UUID(allowNil = false,message = "invalid comment id") @PathVariable(name = "id")java.util.UUID cid){
        return new ResponseEntity<>(this.commentService.getComment(pid,cid), HttpStatus.OK);
//        return new ResponseEntity<>(this.commentService.getComment(cid), HttpStatus.OK);
    }

    @PatchMapping(path = "comments/{id}")
    ResponseEntity<CommentDto> updateComment(
            @UUID(allowNil = false,message = "invalid post id") @PathVariable(name = "postId")java.util.UUID pid,
            @UUID(allowNil = false,message = "invalid comment id") @PathVariable(name = "id")java.util.UUID cid,
            @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(this.commentService.updateComment(pid,cid,commentDto),HttpStatus.OK);
    }

    @DeleteMapping(path = "comments/{id}")
    ResponseEntity<String> deleteComment(
            @UUID(allowNil = false,message = "invalid post id") @PathVariable(name = "postId")java.util.UUID pid,
            @UUID(allowNil = false,message = "invalid post id") @PathVariable(name = "id")java.util.UUID cid){
        return new ResponseEntity<>(String.format("comment for post with post id %s and comment id %s is %s deleted",pid,cid,this.commentService.deleteComment(pid,cid) ? "" : "not"),HttpStatus.OK);
    }
}
