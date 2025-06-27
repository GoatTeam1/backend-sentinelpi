package com.sentinelpi.sentinelpi.controllers;

import com.sentinelpi.sentinelpi.dto.AttackDto;
import com.sentinelpi.sentinelpi.models.Attack;
import com.sentinelpi.sentinelpi.services.AttackService;
import com.sentinelpi.sentinelpi.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attacks")
public class AttackController {

    @Autowired
    private AttackService attackService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        if (size > 1000) size = 1000;
        Pageable pageable = PageRequest.of(page, size);
        Page<Attack> attacks = attackService.findAll(pageable);
        if(attacks.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("No hay ataques registrados", false, HttpStatus.NOT_FOUND));
        }

        return ResponseEntity.ok(new ApiResponse<>(attacks.getContent(), true, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable String id) {
        return attackService.findById(id)
                .<ResponseEntity<ApiResponse<?>>>map(attack ->
                        ResponseEntity.ok(new ApiResponse<>(attack, true, HttpStatus.OK)))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>("Ataque no encontrado", false, HttpStatus.NOT_FOUND)));
    }


    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody AttackDto dto) {
        Attack created = attackService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(created, true, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable String id, @Valid @RequestBody AttackDto dto) {
        return attackService.update(id, dto)
                .<ResponseEntity<ApiResponse<?>>>map(attack -> ResponseEntity.ok((new ApiResponse<>(attack, true, HttpStatus.OK))))
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Ataque no encontrado", false, HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable String id) {
        boolean deleted = attackService.delete(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>("Ataque eliminado", true, HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Ataque no encontrado", false, HttpStatus.NOT_FOUND));
        }
    }
}
