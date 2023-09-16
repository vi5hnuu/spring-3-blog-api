package com.vi5hnu.blogapi.service;

import com.vi5hnu.blogapi.Dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long id,CommentDto commentDto);
    List<CommentDto> getAllComments(Long id);
    CommentDto getComment(Long pid,Long cid);
    CommentDto getComment(Long cid);

    CommentDto updateComment(Long pid,Long cid,CommentDto commentDto);
    CommentDto updateComment(Long cid,CommentDto commentDto);

    boolean deleteComment(Long pid,Long cid);
}
