package com.example.JpaCrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JpaCrud.model.User;
import com.example.JpaCrud.ratelimit.RateLimiterService;
import com.example.JpaCrud.service.UserService;
import com.example.JpaCrud.validation.CheckUserExists;

@RestController
@RequestMapping
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RateLimiterService rateLimiterService;

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        rateLimiterService.validateRequest();
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUserList() {
        rateLimiterService.validateRequest();
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable @CheckUserExists Long id) {
        rateLimiterService.validateRequest();
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser( @Valid @RequestBody User user, @PathVariable("id") @CheckUserExists Long userId) {
        rateLimiterService.validateRequest();
        return ResponseEntity.ok(userService.updateUser(user, userId));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") @CheckUserExists Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}

