package com.vi5hnu.blogapi.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private UUID id;
    @NotBlank(message = "role cannot be blank")
    private String name;
}
