package com.vi5hnu.blogapi.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CommentDto {
    private UUID id;

    @NotBlank(message = "user name cannot be empty.")
    private String name;

    @NotBlank(message = "email is required.")
    @Email(message = "invalid email id.")
    private String email;

    @NotBlank(message = "body cannot be null")
    @Size(min=10,message = "body must have atleast 10 characters")
    private String body;
}
