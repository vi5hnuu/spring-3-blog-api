package com.vi5hnu.blogapi.service.impl;

import com.vi5hnu.blogapi.Dto.LoginDto;
import com.vi5hnu.blogapi.Dto.RegisterDto;
import com.vi5hnu.blogapi.exception.ApiException;
import com.vi5hnu.blogapi.exception.ResourceNotFoundException;
import com.vi5hnu.blogapi.model.Role;
import com.vi5hnu.blogapi.model.User;
import com.vi5hnu.blogapi.repository.RoleRepository;
import com.vi5hnu.blogapi.repository.UserRepository;
import com.vi5hnu.blogapi.security.JwtTokenProvider;
import com.vi5hnu.blogapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {
    final private AuthenticationManager authenticationManager;
    final private UserRepository userRepository;
    final private RoleRepository roleRepository;
    final private PasswordEncoder passwordEncoder;
    final private JwtTokenProvider jwtTokenProvider;
    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider){
        this.authenticationManager=authenticationManager;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtTokenProvider=jwtTokenProvider;
    }
    @Override
    public String login(LoginDto loginDto){
        final Authentication authentication=this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateJwtToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {
        final Role defaultRole=this.roleRepository.findByName("ROLE_USER").orElseThrow(()->new ResourceNotFoundException("role USER not found."));
        if(this.userRepository.existsByUsernameOrEmail(registerDto.getUsername(),registerDto.getEmail())){
            throw new ApiException(String.format("User already exist for %s/%s",registerDto.getUsername(),registerDto.getEmail()), HttpStatus.BAD_REQUEST);
        }
        final User user=new User(null,registerDto.getName(),registerDto.getUsername(),registerDto.getEmail(), passwordEncoder.encode(registerDto.getPassword()), List.of(defaultRole));
        this.userRepository.save(user);
        return "user registered successfully.";
    }
}
