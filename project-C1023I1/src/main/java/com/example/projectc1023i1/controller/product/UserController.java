package com.example.projectc1023i1.controller.product;

import com.example.projectc1023i1.Dto.UserDTO;
import com.example.projectc1023i1.model.Users;
import com.example.projectc1023i1.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private IUserService userService;
    /**
     * Hiển thị tất cả User
     * xuan da them /pagination vi getAllUsers trung voi getAllUsers cua duong
     */
    @GetMapping("/pagination")
    public ResponseEntity<Page<Users>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Users> users = userService.findAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    /**
     * lấy thông tin dựa trên id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Integer id) {
        Users user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * Thêm mới 1 user
     */
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UserDTO userDTO) {
        Users createdUser = userService.save(userDTO, null); // id là null cho tạo mới
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    /**
     * chỉnh sửa thông tin user
     */
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        Users updatedUser = userService.save(userDTO, id); // Sử dụng phương thức save cho cập nhật
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
    public ResponseEntity<Page<Users>> searchUsers(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String numberPhone,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Users> users = userService.searchUsers(userName, fullName, numberPhone, pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



//    ===============================DUOWNG===========
//    ============con 1 phan nua nhung da trung voi code hau o phia tren la  getAllUsers
    @GetMapping("")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello World");
    }


}
