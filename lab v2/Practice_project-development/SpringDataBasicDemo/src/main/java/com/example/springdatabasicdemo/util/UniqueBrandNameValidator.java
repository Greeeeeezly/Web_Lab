package com.example.springdatabasicdemo.util;

import com.example.springdatabasicdemo.repositories.BrandRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueBrandNameValidator implements ConstraintValidator<UniqueBrandName, String> {
    private final BrandRepo brandRepository;

    public UniqueBrandNameValidator(BrandRepo companyRepository) {
        this.brandRepository = companyRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return brandRepository.findByName(value).isEmpty();
    }
}
