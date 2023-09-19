package com.vi5hnu.blogapi.security;

import com.sun.net.httpserver.Headers;
import com.vi5hnu.blogapi.exception.ApiException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.function.ServerRequest;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    final private JwtTokenProvider jwtTokenProvider;
    final private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,UserDetailsService userDetailsService){
        this.jwtTokenProvider=jwtTokenProvider;
        this.userDetailsService=userDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("[JwtAuthenticationFilter] securing");
        final String token=this.getTokenFromRequest(request);
        if(token==null){
            filterChain.doFilter(request,response);
            return;
        }
        System.out.println("[JwtAuthenticationFilter] secured");
        final Claims claims= jwtTokenProvider.validateToken(token);
        final String usernameOrEmail= claims.getSubject();
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(usernameOrEmail);

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());//authenticated user
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }

    private String getTokenFromRequest(HttpServletRequest request){
        String bearerTokenRaw=request.getHeader("Authorization");
        if(bearerTokenRaw==null || !bearerTokenRaw.startsWith("Bearer")){return null;}
        return bearerTokenRaw.substring(7);
    }
}
