package com.sentinelpi.sentinelpi.controllers;

import com.sentinelpi.sentinelpi.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Healtcheck {

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(new ApiResponse<>("Servidor ejecutandose correctamente", true, HttpStatus.OK));
    }
}
