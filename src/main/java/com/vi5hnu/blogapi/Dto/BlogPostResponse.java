package com.vi5hnu.blogapi.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostResponse {
    private List<PostDto> posts;
    private int pageNo;
    private int pageSize;
    private long postsCount;
    private long totalPages;
    private boolean isLast;
}
