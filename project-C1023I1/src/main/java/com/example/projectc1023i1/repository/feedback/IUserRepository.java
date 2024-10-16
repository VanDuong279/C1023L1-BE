package com.example.projectc1023i1.repository.feedback;

import com.example.projectc1023i1.model.feedback.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<Users, Integer> {
}
