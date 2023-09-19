package com.vi5hnu.blogapi.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType="Bearer";

    public JwtAuthResponse setToken(String token){
        this.accessToken=token;
        return this;
    }
}
