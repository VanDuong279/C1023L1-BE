package com.example.projectc1023i1.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameExistsValidator.class)
public @interface UsernameExists {
    String message() default "So dien thoai nay da ton tai trong he thong";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
