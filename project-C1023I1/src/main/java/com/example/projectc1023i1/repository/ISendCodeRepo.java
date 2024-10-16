package com.example.projectc1023i1.repository;

import com.example.projectc1023i1.model.SendCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ISendCodeRepo extends JpaRepository<SendCode, Long> {
    @Query(value = "SELECT s.checkCode FROM SendCode as s WHERE s.email = :email",nativeQuery = true)
    String findByEmailCode(@Param("email") String email);
    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "delete from SendCode as s where s.email = :email", nativeQuery = true)
    void delete(@Param("email") String sendCode);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SendCode (checkcode, email) VALUES (:checkCode, :email)", nativeQuery = true)
    void saveSendCode(String checkCode, String email);
}