package com.example.projectc1023i1.service.feedback;

import com.example.projectc1023i1.model.feedback.Users;
import com.example.projectc1023i1.repository.feedback.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
