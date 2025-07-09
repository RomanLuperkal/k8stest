package org.ivanov.frontservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${user-service.url}")
    private String host;


    @GetMapping("{userId}")
    public ResponseEntity<Users> getUser(@PathVariable Long userId) {
        log.info("getUser: userId={}", userId);
        return ResponseEntity.ok(restTemplate.getForObject(host + "/user/" + userId, Users.class));
    }
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        log.info("createUser: user={}", user);
        return ResponseEntity.ok(restTemplate.postForObject(host + "/user", user, Users.class));
    }

    private record Users(Long user_id, String username) {}
}
