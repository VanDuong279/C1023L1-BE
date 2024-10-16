package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE " +
            "(:userName IS NULL OR LOWER(u.userName) LIKE LOWER(CONCAT('%', :userName, '%'))) AND " +
            "(:fullName IS NULL OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))) AND " +
            "(:numberPhone IS NULL OR u.numberPhone LIKE CONCAT('%', :numberPhone, '%'))")
    Page<User> searchUsers(
            @Param("userName") String userName,
            @Param("fullName") String fullName,
            @Param("numberPhone") String numberPhone,
            Pageable pageable);
    // validate cho userName
    boolean existsByUserName(String userName);

}
