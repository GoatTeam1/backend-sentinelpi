//package com.sentinelpi.sentinelpi.exceptions;
//
//import com.sentinelpi.sentinelpi.utils.ApiResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.*;
//import jakarta.servlet.http.HttpServletRequest;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(err -> {
//            errors.put(err.getField(), err.getDefaultMessage());
//        });
//
//        ApiResponse<Map<String, String>> response = new ApiResponse<>(errors, false, HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(EmailAlreadyExistsException.class)
//    public ResponseEntity<ApiResponse<String>> handleEmailExists(EmailAlreadyExistsException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(new ApiResponse<>(ex.getMessage(), false, HttpStatus.BAD_REQUEST));
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
//        ApiResponse<String> response = new ApiResponse<>("Internal server error: " + ex.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
