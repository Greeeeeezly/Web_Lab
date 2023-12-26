package com.example.springdatabasicdemo.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public class UUIDValidation implements ConstraintValidator<ValidUUID, UUID> {

    @Override
    public void initialize(ValidUUID constraintAnnotation) {
    }

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context) {
        return value != null && !value.equals(UUID.fromString("00000000-0000-0000-0000-000000000000"));
    }
}
