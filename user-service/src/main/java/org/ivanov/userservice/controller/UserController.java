package org.ivanov.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ivanov.userservice.model.Users;
import org.ivanov.userservice.repository.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UsersRepository usersRepository;

    @GetMapping("{userId}")
    public ResponseEntity<Users> getUser(@PathVariable Long userId) {
        log.info("Get user with id {}", userId);
        return usersRepository.findById(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        log.info("Create user {}", user);
        return ResponseEntity.ok(usersRepository.save(user));
    }
}
