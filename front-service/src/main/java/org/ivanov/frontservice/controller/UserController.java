package org.ivanov.frontservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${user-service.url}")
    private String host;


    @GetMapping("{userId}")
    public ResponseEntity<Users> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(restTemplate.getForObject(host + "/" + userId, Users.class));
    }
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        return ResponseEntity.ok(restTemplate.postForObject(host, user, Users.class));
    }

    private record Users(Long user_id, String username) {}
}
