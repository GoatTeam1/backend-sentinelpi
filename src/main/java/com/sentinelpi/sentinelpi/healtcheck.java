package com.sentinelpi.sentinelpi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healtcheck {
    @GetMapping("/health")
    public String healthCheck() {
        return "Connected to SentinelPi";
    }
}
