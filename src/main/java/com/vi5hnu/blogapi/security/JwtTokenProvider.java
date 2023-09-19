package com.vi5hnu.blogapi.security;

import com.vi5hnu.blogapi.exception.ApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}") private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}") private long jwtExpirationMilliSeconds;

    public String generateJwtToken(Authentication authentication){
        final String usernameOrEmail=authentication.getName();
        final Date curDate=new Date();
        final Date expireDate=new Date(curDate.getTime()+jwtExpirationMilliSeconds);
        return Jwts.builder()
                .setSubject(usernameOrEmail)
                .setIssuedAt(curDate)
                .setExpiration(expireDate)
                .signWith(this.key(), SignatureAlgorithm.HS256).compact();
    }

    private Key key(){
        final byte[] decodedSecret=Decoders.BASE64.decode(this.jwtSecret);
        System.out.println(Arrays.toString(decodedSecret));
        return Keys.hmacShaKeyFor(decodedSecret);
    }
    public Claims validateToken(String token){
            try{
                return Jwts.parserBuilder()
                        .setSigningKey(this.key())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            }catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex){
                throw new ApiException(ex.getMessage(), HttpStatus.BAD_REQUEST);
            }
    }


}
