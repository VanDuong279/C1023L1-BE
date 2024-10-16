package com.example.projectc1023i1.service.user;

import com.example.projectc1023i1.Dto.UserDTO;
import com.example.projectc1023i1.model.Users;

import java.util.Optional;

public interface IUserService {
    void createUser(UserDTO userDTO);
    String login (String username, String password);
    boolean checkUsername(String username);
    boolean checkNumberphone(String numberphone);
    boolean exitsEmail(String email);
    boolean isPasswordExpired(Users Users);
    String updatePassword(Users Users);
    Optional<Users> findByUsername(String username);
    UserDTO ConverDTO(Users Users);
    Users findByPhone(String phone);

}