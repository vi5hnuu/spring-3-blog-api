package com.vi5hnu.blogapi.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "username or email cannot be empty")
    private String usernameOrEmail;

    @NotBlank(message = "password cannot be blank")
    private String password;
}
