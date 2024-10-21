package com.example.projectc1023i1.service.user.impl;

import com.example.projectc1023i1.Dto.SendCodeDTO;
import com.example.projectc1023i1.model.SendCode;
import com.example.projectc1023i1.repository.ISendCodeRepo;
import com.example.projectc1023i1.service.user.ISendCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendCodeService implements ISendCodeService {
    @Autowired
    private ISendCodeRepo sendCodeRepo;


    /**
     * luu du lieu gom email va code vao trong database
     * @param session day chinh la du lieu cua bang Session
     */
    @Override
    public void save(SendCodeDTO session) {
        SendCode sessionEntity = new SendCode();
        sessionEntity.setCheckCode(session.getCode());
        sessionEntity.setEmail(session.getEmail());
        sendCodeRepo.saveSendCode(sessionEntity.getCheckCode(), session.getEmail());
    }


    /**
     * tim kiem email
     * @param email email nguoi dung nhap
     * @return tra ve 1 chuoi
     */
    @Override
    public SendCode findByEmail(String email) {

        return sendCodeRepo.findByEmail(email);
    }

    /**
     * xoa 1 du lieu bang SendCode dua vao email
     * @param email email nguoi dung nhap vao
     */
    @Override
    public void delete(SendCode sendCode) {
        sendCodeRepo.delete(sendCode);
    }
}
