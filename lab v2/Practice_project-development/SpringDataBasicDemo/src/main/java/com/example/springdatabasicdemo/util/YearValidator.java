package com.example.springdatabasicdemo.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearValidator implements ConstraintValidator<ValidEndYear, Integer> {

    @Override
    public void initialize(ValidEndYear constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Value cannot be null").addConstraintViolation();
            return false;
        }
        int currentYear = Year.now().getValue();
        return value <= currentYear;
    }
}
