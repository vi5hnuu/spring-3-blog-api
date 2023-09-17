package com.vi5hnu.blogapi.security;

import com.vi5hnu.blogapi.model.User;
import com.vi5hnu.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetails implements UserDetailsService {
    final private UserRepository userRepository;

    @Autowired
    CustomUserDetails(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        final User user = this.userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(()->new UsernameNotFoundException(String.format("user does not exist for username/email %s",usernameOrEmail)));
        final List<SimpleGrantedAuthority> authorities=user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName())).toList();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities );
    }
}
