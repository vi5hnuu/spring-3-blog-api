package com.vi5hnu.blogapi.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;

    @NotBlank(message = "post title cannot be blank...")
    @Size(min = 2,message = "post title must have atleast 2 characters,")
    private String title;

    @NotBlank(message = "post description cannot be blank")
    @Size(min = 10,message = "description must be atleast 10 characters long")
    private String description;

    @NotBlank(message = "post description cannot be blank")
    @Size(min = 20,message = "content must be atleast 20 characters long")
    private String content;
    private List<CommentDto> comments;
}
