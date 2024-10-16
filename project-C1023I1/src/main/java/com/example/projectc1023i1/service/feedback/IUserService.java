package com.example.projectc1023i1.service.feedback;

import com.example.projectc1023i1.model.feedback.Users;

import java.util.List;

public interface IUserService {

    public List<Users> getAllUsers();

    public Users getUserById(Integer userId);
}
