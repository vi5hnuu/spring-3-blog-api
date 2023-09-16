package com.vi5hnu.blogapi.config;

import jakarta.servlet.FilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.SecureRandom;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.csrf().disable();
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize)->authorize.requestMatchers(HttpMethod.GET,"api/v1/**")
                        .permitAll().anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()).build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        SecureRandom saltGenHelper=new SecureRandom();
        return new BCryptPasswordEncoder(6,saltGenHelper);
    }
    @Bean
    UserDetailsService userDetailsService(){
        UserDetails vishnu= User.builder().passwordEncoder(passwordEncoder()::encode).username("vishnu").password("vi5hnu").roles("ADMIN").build();
        UserDetails krishan= User.builder().passwordEncoder(passwordEncoder()::encode).username("krishan").password("krishan").roles("USER").build();
        return new InMemoryUserDetailsManager(vishnu,krishan);
    }
}
