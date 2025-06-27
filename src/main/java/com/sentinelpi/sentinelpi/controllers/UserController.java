package com.sentinelpi.sentinelpi.controllers;

import com.sentinelpi.sentinelpi.dto.UpdateUserDto;
import com.sentinelpi.sentinelpi.dto.UserDto;
import com.sentinelpi.sentinelpi.models.User;
import com.sentinelpi.sentinelpi.services.UserService;
import com.sentinelpi.sentinelpi.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("No users found", false, HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok(new ApiResponse<>(users, true, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getUser(@PathVariable String id) {
        return userService.findById(id)
                .<ResponseEntity<ApiResponse<?>>>map(user -> ResponseEntity.ok(new ApiResponse<>(user, true, HttpStatus.OK)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("User not found", false, HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody UserDto dto) {
        User created = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(created, true, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserDto dto) {
        return userService.update(id, dto)
                .<ResponseEntity<ApiResponse<?>>>map(user -> ResponseEntity.ok(new ApiResponse<>(user, true, HttpStatus.OK)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("User not found", false, HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable String id) {
        boolean deleted = userService.delete(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>("User deleted", true, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("User not found", false, HttpStatus.NOT_FOUND));
    }
}
