package com.vi5hnu.blogapi.service;

import com.vi5hnu.blogapi.Dto.CommentDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    CommentDto createComment(UUID id, CommentDto commentDto);
    List<CommentDto> getAllComments(UUID id);
    CommentDto getComment(UUID pid, UUID cid);
    CommentDto getComment(UUID cid);

    CommentDto updateComment(UUID pid, UUID cid, CommentDto commentDto);
    CommentDto updateComment(UUID cid, CommentDto commentDto);

    boolean deleteComment(UUID pid, UUID cid);
}
