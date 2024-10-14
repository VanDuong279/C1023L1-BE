package com.example.projectc1023i1.service.product;

import com.example.projectc1023i1.Dto.product.UserDTO;
import com.example.projectc1023i1.model.product.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<User> findAll(Pageable pageable);
    User findById(Integer id);
    User save(UserDTO userDTO, Integer id); // Phương thức duy nhất để thêm mới và cập nhật
    void delete(Integer id);
    Page<User> searchUsers(String useName, String fullName, String numberPhone, Pageable pageable); // Tìm kiếm với phân trang
}
