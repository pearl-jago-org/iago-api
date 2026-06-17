package com.iago.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ApiController {

    private final RestTemplate restTemplate = new RestTemplate();

    // GET /api → estado general
    @GetMapping("/api")
    public Map<String, String> api() {
        Map<String, String> response = new HashMap<>();

        String users = restTemplate.getForObject("http://iago-users:8080/users", String.class);
        String orders = restTemplate.getForObject("http://iago-orders:8080/orders", String.class);

        response.put("users", users);
        response.put("orders", orders);

        return response;
    }

    // GET /users → proxy al micro iago-users
    @GetMapping("/users")
    public Object getUsers() {
        return restTemplate.getForObject("http://iago-users:8080/users", Object.class);
    }

    // POST /users → crea usuario vía API Gateway
    @PostMapping("/users")
    public Object createUser(@RequestBody Map<String, Object> body) {
        return restTemplate.postForObject("http://iago-users:8080/users", body, Object.class);
    }

    // GET /orders → proxy al micro iago-orders
    @GetMapping("/orders")
    public Object getOrders() {
        return restTemplate.getForObject("http://iago-orders:8080/orders", Object.class);
    }

    // POST /orders → crea order vía API Gateway (si lo usas)
    @PostMapping("/orders")
    public Object createOrder(@RequestBody Map<String, Object> body) {
        return restTemplate.postForObject("http://iago-orders:8080/orders", body, Object.class);
    }
}
