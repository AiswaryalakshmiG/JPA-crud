package com.example.JpaCrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.JpaCrud.model.User;
import com.example.JpaCrud.ratelimit.RateLimiterService;
import com.example.JpaCrud.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RateLimiterService rateLimiterService;

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        rateLimiterService.validateRequest();
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> getUserList() {
        rateLimiterService.validateRequest();
        return userService.getUserList();
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
        rateLimiterService.validateRequest();
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") Long userId) {
        rateLimiterService.validateRequest();
        userService.deleteUserById(userId);
        return "Deleted Successfully";
    }
}
