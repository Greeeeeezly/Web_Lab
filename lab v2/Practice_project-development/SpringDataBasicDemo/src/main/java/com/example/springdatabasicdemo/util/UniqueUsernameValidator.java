package com.example.springdatabasicdemo.util;

import com.example.springdatabasicdemo.repositories.BrandRepo;
import com.example.springdatabasicdemo.repositories.UserRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserRepo userRepository;

    public UniqueUsernameValidator(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepository.findByUsername(value).isEmpty();
    }
}
