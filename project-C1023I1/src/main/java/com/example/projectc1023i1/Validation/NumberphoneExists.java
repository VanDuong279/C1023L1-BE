package com.example.projectc1023i1.Validation;

import jakarta.validation.Constraint;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberphoneExistsValidator.class)
public @interface NumberphoneExists {
    String message() default "So dien thoai nay da ton tai";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
