package com.vi5hnu.blogapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vi5hnu.blogapi.exception.ErrorDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> errorResponse = new HashMap<>();
        System.out.println("got an exception : "+authException.toString());
        errorResponse.put("message", authException.getMessage());
        errorResponse.put("date", new Date().toString());
        errorResponse.put("uri",request.getRequestURI());

        ObjectMapper objectMapper = new ObjectMapper();
        try (OutputStream out = response.getOutputStream()) {
            objectMapper.writeValue(out, errorResponse);
        }
    }
}
