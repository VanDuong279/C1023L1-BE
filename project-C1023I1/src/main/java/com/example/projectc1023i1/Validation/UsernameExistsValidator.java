package com.example.projectc1023i1.Validation;

import com.example.projectc1023i1.repository.IUserRepository;
import com.example.projectc1023i1.service.user.IUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UsernameExistsValidator implements ConstraintValidator<UsernameExists, String> {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public void initialize(UsernameExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository == null) {
            return false;
        }
        return !userRepository.existsByUsername(s);
    }
}
