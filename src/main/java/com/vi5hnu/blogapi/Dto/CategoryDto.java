package com.vi5hnu.blogapi.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    @NotBlank(message = "category name is required")
    private String name;

    @NotBlank(message = "category description is required")
    @Size(min = 20,message = "description must be atleast 20 character long")
    private String description;
}
