package com.sentinelpi.sentinelpi.controllers;

import com.sentinelpi.sentinelpi.dto.LoginDto;
import com.sentinelpi.sentinelpi.dto.JwtResponseDto;
import com.sentinelpi.sentinelpi.services.AuthService;
import com.sentinelpi.sentinelpi.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<JwtResponseDto>> signIn(@RequestBody @Valid LoginDto dto) {
        String token = authService.login(dto.getUsername(), dto.getPassword());

        if (token == null || token.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(null, false, HttpStatus.UNAUTHORIZED));
        }

        JwtResponseDto response = new JwtResponseDto(token, dto.getUsername());
        return ResponseEntity.ok(new ApiResponse<>(response, true, HttpStatus.OK));
    }
}
