package com.example.projectc1023i1.repository.bill;


import com.example.projectc1023i1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    // Các phương thức tùy chỉnh nếu cần
}
