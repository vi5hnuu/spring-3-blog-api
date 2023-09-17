package com.vi5hnu.blogapi.Dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @Email
    @NotBlank(message = "email cannot be empty")
    private String email;
    private String password;
    private String confirmPassword;
    @AssertTrue(message = "password and confirmPassword must match/must be atleast 7 characters long")
    private boolean isPasswordMatching() {
        return password != null && password.equals(confirmPassword) && password.length()>=7;
    }
}
