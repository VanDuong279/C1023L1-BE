package com.example.projectc1023i1.repository;

import com.example.projectc1023i1.model.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

public interface IUserRepo extends CrudRepository<Users, Integer> {
    boolean existsByUsername(String username); // hafm kiem tra tai khoan co ton tai hay khong
    boolean existsByEmail(String email);
    boolean existsByNumberphone(String numberphone);

    Optional<Users> findByUsername(String username);
    Optional<Users> findByNumberphone(String numberphone);
    @Modifying
    @Transactional
    @Query(value = "insert into users (full_name, address, numberphone, email, user_name, `password`, birthday, is_active, role_id) " +
            "values (:fullName, :address, :numberphone, :email, :username, :password, :birthday, :isActive, :roleId)",
            nativeQuery = true)
    void saves(@Param("fullName") String fullName,
               @Param("address") String address,
               @Param("numberphone") String numberphone,
               @Param("email") String email,
               @Param("username") String username,
               @Param("password") String password,
               @Param("birthday") Date birthday,
               @Param("isActive") boolean isActive,
               @Param("roleId") int roleId);


}