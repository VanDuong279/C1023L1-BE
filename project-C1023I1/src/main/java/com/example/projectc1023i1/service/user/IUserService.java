package com.example.projectc1023i1.service.user;

import com.example.projectc1023i1.Dto.UserDTO;
import com.example.projectc1023i1.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    Page<Users> findAll(Pageable pageable);
    Users findById(Integer id);
    Users save(UserDTO userDTO, Integer id); // Phương thức duy nhất để thêm mới và cập nhật
    void delete(Integer id);
    Page<Users> searchUsers(String useName, String fullName, String numberPhone, Pageable pageable);
}