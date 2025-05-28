package com.example.JpaCrud.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JpaCrud.metrics.MetricsConfig;
import com.example.JpaCrud.model.User;
import com.example.JpaCrud.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MetricsConfig metricsConfig;

    @Override
    public User saveUser(User user) {
        metricsConfig.incrementUserCreated();
        return userRepository.save(user);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user, Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (Objects.nonNull(user.getName()) && !user.getName().isBlank()) {
            existingUser.setName(user.getName());
        }

        if (Objects.nonNull(user.getEmail()) && !user.getEmail().isBlank()) {
            existingUser.setEmail(user.getEmail());
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User ID " + id + " : User not found"));
    }
}

