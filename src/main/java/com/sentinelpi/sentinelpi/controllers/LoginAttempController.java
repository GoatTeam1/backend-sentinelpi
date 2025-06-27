package com.sentinelpi.sentinelpi.controllers;

import com.sentinelpi.sentinelpi.dto.LoginAttempDto;
import com.sentinelpi.sentinelpi.models.LoginAttemp;
import com.sentinelpi.sentinelpi.services.LoginAttempService;
import com.sentinelpi.sentinelpi.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login-attempts")
public class LoginAttempController {

    @Autowired
    private LoginAttempService loginAttempService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll() {
        List<LoginAttemp> attempts = loginAttempService.findAll();
        if (attempts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("No hay intentos de login registrados", false, HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok(new ApiResponse<>(attempts, true, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable String id) {
        return loginAttempService.findById(id)
                .<ResponseEntity<ApiResponse<?>>>map(attempt ->
                        ResponseEntity.ok(new ApiResponse<>(attempt, true, HttpStatus.OK)))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>("Intento de login no encontrado", false, HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody LoginAttempDto dto) {
        LoginAttemp created = loginAttempService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(created, true, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable String id, @Valid @RequestBody LoginAttempDto dto) {
        return loginAttempService.update(id, dto)
                .<ResponseEntity<ApiResponse<?>>>map(updated ->
                        ResponseEntity.ok(new ApiResponse<>(updated, true, HttpStatus.OK)))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>("Intento de login no encontrado", false, HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable String id) {
        boolean deleted = loginAttempService.delete(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>("Intento de login eliminado", true, HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Intento de login no encontrado", false, HttpStatus.NOT_FOUND));
        }
    }
}
