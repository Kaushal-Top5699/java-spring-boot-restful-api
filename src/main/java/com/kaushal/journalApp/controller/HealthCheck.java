package com.kaushal.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheck {

    @GetMapping("health-check")
    public Map<String, String> healCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("health", "OK!");
        return response;
    }
}
