package com.example.JpaCrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.JpaCrud.model.User;
import com.example.JpaCrud.service.UserService;

import java.util.List;

@RestController
public class UserController {
@Autowired
private UserService userService;

@PostMapping("/users")
public User saveUser( @RequestBody User user) {
	return userService.saveUser(user);
}

@GetMapping("/users")
public List<User> getUserList(){
	return userService.getUserList();
	}

@PutMapping("/users/{id}")
public User updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
    return userService.updateUser(user,userId);
}

@DeleteMapping("/users/{id}")
public String deleteUserById(@PathVariable("id") Long userId) {
    userService.deleteUserById(userId);
    return "Deleted Successfully";
}
}
