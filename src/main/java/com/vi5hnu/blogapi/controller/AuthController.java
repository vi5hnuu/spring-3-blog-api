package com.vi5hnu.blogapi.controller;

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
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto){
        return new ResponseEntity<>(this.authService.login(loginDto), HttpStatus.OK);
    }
    @PostMapping(value = {"register","signup"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(this.authService.register(registerDto), HttpStatus.CREATED);
    }
}
