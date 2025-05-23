package com.example.JpaCrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JpaCrud.exception.UserNotFoundException;
import com.example.JpaCrud.metrics.MetricsConfig;
import com.example.JpaCrud.model.User;
import com.example.JpaCrud.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MetricsConfig metrics;

    @Override
    public User saveUser(User user) {
        if (user.getName() == null || user.getName().length() < 5) {
            throw new IllegalArgumentException("Username must be at least 5 characters long.");
        }
        metrics.incrementUserCreated();
        return userRepository.save(user);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user, Long userId) {
        User existing = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        if (user.getName() == null || user.getName().length() < 5) {
            throw new IllegalArgumentException("Username must be at least 5 characters long.");
        }
        if (user.getEmail() == null || !user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") || !user.getEmail().contains(".com")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return userRepository.save(existing);
    }

    @Override
    public void deleteUserById(Long userId) {
    	User user = userRepository.findById(userId)
    		    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

    		System.out.println("Deleting user: " + user.getName());
    		userRepository.deleteById(userId);
    		metrics.incrementUserDeleted();
    }
}