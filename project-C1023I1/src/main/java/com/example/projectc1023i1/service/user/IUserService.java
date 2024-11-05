package com.example.projectc1023i1.service.user;

import com.example.projectc1023i1.Dto.EmployeeDTO;
import com.example.projectc1023i1.Dto.UserDTO;
import com.example.projectc1023i1.model.Users;
import com.example.projectc1023i1.respone.UserInforRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    void createUser(UserDTO userDTO);
    String login (String username, String password);
    boolean exitsUsername(String username);
    boolean exitsNumberphone(String numberphone);
    boolean exitsEmail(String email);
    boolean isPasswordExpired(Users Users);
    String updatePassword(Users Users);
    Optional<Users> findByUsername(String username);
    UserDTO ConverDTO(Users Users);
    UserInforRespone converUser(Users Users);
    Users findByPhone(String phone);
    void changePassword(Users Users);
    void updateUsersByImgUrlAndUserId(String imgUrlA, Integer userId);

    // hau
    Page<Users> findAll(Pageable pageable);
    Users findById(Integer id);
    Users save(EmployeeDTO employeeDTO, Integer id); // Phương thức duy nhất để thêm mới và cập nhật
    void delete(Integer id);
    Page<Users> searchUsers(String useName, String fullName, String numberPhone, Pageable pageable);

    // duong
    public List<Users> getAllUsers();

    public Users getUserById(Integer userId);
}