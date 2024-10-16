package com.example.projectc1023i1.Validation;

import com.example.projectc1023i1.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public void initialize(UniqueUsername constrainAnnotation){}

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        if (userRepository==null){
            return true;
        }
        return !userRepository.existsByUsername(userName);
    }
}
