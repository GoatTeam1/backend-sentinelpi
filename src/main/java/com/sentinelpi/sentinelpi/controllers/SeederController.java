package com.sentinelpi.sentinelpi.controllers;

import com.sentinelpi.sentinelpi.seeders.SeederService;
import com.sentinelpi.sentinelpi.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seed")
public class SeederController {

    @Autowired
    private SeederService seederService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> seedData(
            @RequestParam String collection,
            @RequestParam(defaultValue = "100") int cant
    ) {
        try {
            int inserted = seederService.seed(collection.toLowerCase(), cant);
            return ResponseEntity.ok(new ApiResponse<>("Insertados " + inserted + " registros", true, HttpStatus.OK));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST));
        }
    }
}
