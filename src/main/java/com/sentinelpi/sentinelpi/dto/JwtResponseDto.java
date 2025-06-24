package com.sentinelpi.sentinelpi.dto;

import lombok.Data;

@Data
public class JwtResponseDto {
    private String token;
    private String username;

    public JwtResponseDto(String token, String username) {
        this.token = token;
        this.username = username;
    }

}
