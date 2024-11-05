package com.example.projectc1023i1.controller.product;

import com.example.projectc1023i1.Dto.EmployeeDTO;
import com.example.projectc1023i1.Dto.EmployeeUpdateDTO;
import com.example.projectc1023i1.Dto.UserDTO;
import com.example.projectc1023i1.model.Users;
import com.example.projectc1023i1.service.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
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

        // Lấy dữ liệu có phân trang từ database
        Page<Users> usersPage = userService.findAll(PageRequest.of(0, Integer.MAX_VALUE));

        // Lọc danh sách người dùng có lương lớn hơn 0 và sắp xếp theo role
        List<Users> filteredUsers = usersPage.getContent().stream()
                .filter(user -> user.getSalary() != null && user.getSalary() > 0)
                .sorted(Comparator.comparing(user -> user.getRole().getRoleName().equals("ROLE_USER") ? 0 : 1))
                .collect(Collectors.toList());

        // Thực hiện phân trang thủ công trên danh sách đã lọc
        int start = Math.min((int)page * size, filteredUsers.size());
        int end = Math.min(start + size, filteredUsers.size());
        List<Users> paginatedUsers = filteredUsers.subList(start, end);

        // Tạo đối tượng Page cho danh sách đã phân trang thủ công
        Page<Users> filteredPage = new PageImpl<>(paginatedUsers, pageable, filteredUsers.size());

        return new ResponseEntity<>(filteredPage, HttpStatus.OK);
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
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        System.out.println("Received salary: " + employeeDTO.getSalary());

        // Kiểm tra xem có lỗi validation hay không
        if (bindingResult.hasErrors()) {
            // Trả về danh sách các thông báo lỗi gọn gàng
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage()) // Lấy thông báo lỗi từ mỗi fieldError
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }

        // Nếu không có lỗi, tiếp tục thêm mới
        Users createdUser = userService.save(employeeDTO); // id là null cho tạo mới
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Update 1 user
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @Valid @RequestBody EmployeeUpdateDTO employeeUpdateDTO, BindingResult bindingResult) {
        // Kiểm tra xem có lỗi validation hay không
        if (bindingResult.hasErrors()) {
            // Trả về danh sách các thông báo lỗi gọn gàng
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage()) // Lấy thông báo lỗi từ mỗi fieldError
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }

        // Nếu không có lỗi, tiếp tục cập nhật
        Users updatedUser = userService.update(employeeUpdateDTO, id); // Sử dụng phương thức save cho cập nhật
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
            @RequestParam(required = false) Integer minSalary,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Users> users = userService.searchUsers(userName, fullName, minSalary,numberPhone, pageable);
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authenticated user: " + authentication.getName());
        return ResponseEntity.ok("Hello World");
    }


}
