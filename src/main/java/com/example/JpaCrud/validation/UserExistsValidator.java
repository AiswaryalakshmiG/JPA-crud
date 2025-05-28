package com.example.JpaCrud.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.JpaCrud.repository.UserRepository;


@Component
public class UserExistsValidator implements ConstraintValidator<CheckUserExists, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return userRepository.existsById(id);
    }
}
