package com.example.projectc1023i1.controller.product;

import com.example.projectc1023i1.Dto.product.UserDTO;
import com.example.projectc1023i1.model.product.User;
import com.example.projectc1023i1.service.product.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private IUserService userService;
    /**
     * Hiển thị tất cả User
     */
    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.findAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    /**
     * lấy thông tin dựa trên id
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Thêm mới 1 user
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User createdUser = userService.save(userDTO, null); // id là null cho tạo mới
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    /**
     * chỉnh sửa thông tin user
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.save(userDTO, id); // Sử dụng phương thức save cho cập nhật
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * xóa 1 user dựa trên id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /**
     * tìm kiếm user dựa vào userName,fullName, numberPhone có phân trang
     */
    @GetMapping("/search")
    public ResponseEntity<Page<User>> searchUsers(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String numberPhone,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.searchUsers(userName, fullName, numberPhone, pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
