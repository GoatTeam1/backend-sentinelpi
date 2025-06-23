package com.sentinelpi.sentinelpi.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private T result;
    private boolean response;
    private HttpStatus status;
}
