package com.example.projectc1023i1.Validation;

import jakarta.validation.Constraint;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.messaging.handler.annotation.Payload;

import javax.lang.model.element.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailExistsValidator.class)
public @interface EmailExists {
    String message() default "Email already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
