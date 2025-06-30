package org.ivanov.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.ivanov.userservice.model.Users;
import org.ivanov.userservice.repository.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UsersRepository usersRepository;

    @GetMapping("{userId}")
    public ResponseEntity<Users> getUser(@PathVariable Long userId) {
        return usersRepository.findById(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Users> createUser(Users user) {
        return ResponseEntity.ok(usersRepository.save(user));
    }
}
