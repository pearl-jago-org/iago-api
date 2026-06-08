package com.iago.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/api")
    public Map<String, String> api() {
        Map<String, String> response = new HashMap<>();

        String users = restTemplate.getForObject("http://iago-users:8080/users", String.class);
        String orders = restTemplate.getForObject("http://iago-orders:8080/orders", String.class);

        response.put("users", users);
        response.put("orders", orders);

        return response;
    }
}

