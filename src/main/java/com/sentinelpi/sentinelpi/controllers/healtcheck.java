package com.sentinelpi.sentinelpi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class healtcheck {

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("Servidor activo");
    }
}
