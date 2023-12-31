package com.vi5hnu.blogapi.controller;

import com.vi5hnu.blogapi.Dto.JwtAuthResponse;
import com.vi5hnu.blogapi.Dto.LoginDto;
import com.vi5hnu.blogapi.Dto.RegisterDto;
import com.vi5hnu.blogapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {
    final private AuthService authService;
    @Autowired
    AuthController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping(value = {"login","signin"})
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginDto loginDto){
        String token=this.authService.login(loginDto);
        return new ResponseEntity<>(new JwtAuthResponse().setToken(token), HttpStatus.OK);
    }
    @PostMapping(value = {"register","signup"})
    public ResponseEntity<JwtAuthResponse> register(@Valid @RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(new JwtAuthResponse().setToken(this.authService.register(registerDto)), HttpStatus.CREATED);
    }
}
