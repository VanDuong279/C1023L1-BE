package com.example.projectc1023i1.Validation;

import com.example.projectc1023i1.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailExistsValidator implements ConstraintValidator<EmailExists, String> {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public void initialize(EmailExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository == null) {
            return true;
        }
        return !userRepository.existsByEmail(email);
    }
}
