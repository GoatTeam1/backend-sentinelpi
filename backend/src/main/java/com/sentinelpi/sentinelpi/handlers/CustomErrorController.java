package com.sentinelpi.sentinelpi.handlers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("result", "Ruta no encontrada");
        body.put("response", false);
        body.put("status", HttpStatus.NOT_FOUND.name());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
