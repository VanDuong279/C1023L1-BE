package com.example.projectc1023i1.service.user;

import com.example.projectc1023i1.Dto.SendCodeDTO;
import com.example.projectc1023i1.model.Users;

public interface ISendCodeService {
    void save(SendCodeDTO session);
    String findByEmail(String email);
    void delete(String email);
}
