package com.vi5hnu.blogapi.service;

import com.vi5hnu.blogapi.Dto.LoginDto;
import com.vi5hnu.blogapi.Dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
