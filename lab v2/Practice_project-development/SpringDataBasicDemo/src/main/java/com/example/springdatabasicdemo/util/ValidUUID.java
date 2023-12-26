package com.example.springdatabasicdemo.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UUIDValidation.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUUID {
    String message() default "Invalid UUID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
