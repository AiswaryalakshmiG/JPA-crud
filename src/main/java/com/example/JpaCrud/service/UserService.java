package com.example.JpaCrud.service;

import java.util.List;

import com.example.JpaCrud.model.User;

public interface UserService {
    User saveUser(User user);
    List<User> getUserList();
    User updateUser(User user, Long userId);
    void deleteUserById(Long userId);
    User getUserById(Long id);
}