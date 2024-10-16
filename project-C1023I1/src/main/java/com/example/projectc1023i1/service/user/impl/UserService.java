package com.example.projectc1023i1.service.user.impl;

import com.example.projectc1023i1.Dto.UserDTO;
import com.example.projectc1023i1.component.JwtTokenUtils;
import com.example.projectc1023i1.model.Roles;
import com.example.projectc1023i1.model.Users;
import com.example.projectc1023i1.repository.IRoleRepo;
import com.example.projectc1023i1.repository.IUserRepo;
import com.example.projectc1023i1.service.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IRoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private ModelMapper modelMapper;


    /**
     * tao tai khoan
     * @param userDTO thong tin cua nguoi dung muon tao tai khoan
     */
    @Override
    public void createUser(UserDTO userDTO) {
        Users users = new Users();



        users.setFullName(userDTO.getFullName() != null ? userDTO.getFullName() : "");
        users.setAddress(userDTO.getAddress() != null ? userDTO.getAddress() : "");
        users.setEmail(userDTO.getEmail());
        users.setNumberphone(userDTO.getNumberphone());
        users.setUsername(userDTO.getUsername());

        Date birthday = (Date) userDTO.getBirthday();
        users.setIsActive(true);
        Roles roles = roleRepo.findByRoleId(1);
        if (roles != null) {
            users.setRole(roles);
        }else  {
            throw new DataIntegrityViolationException("Role nay khong ton tai");
        }

        String password = passwordEncoder.encode(userDTO.getPassword());
        users.setPassword(password);

        userRepo.saves(users.getFullName(),
                users.getAddress(),
                users.getNumberphone(),
                users.getEmail(),
                users.getUsername(),
                users.getPassword(),
                birthday,
                users.getIsActive(),
                users.getRole().getRoleId());
    }

    /**
     * dang nhap bang username va password sex tra ve 1 token
     * @param username  tai khoan nguoi dung
     * @param password  password nguoi dung
     * @return tra ve 1 token
     */
    @Override
    public String login(String username, String password) {
        Optional<Users> user = userRepo.findByUsername(username); // kiem tra tai khoan
        Users userExist = user.get();
        if (!user.isPresent()) {
            throw new DataIntegrityViolationException("Username already exists"); // kiem tra tai khoan
        }
        if (!passwordEncoder.matches(password, userExist.getPassword())) {
            throw new DataIntegrityViolationException("mat khau bi sai");// kiem tra mat khau
        }

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
        // tao token chua thong tin nguoi dung va mat khau xac thuc
        authenticationManager.authenticate(auth); // xac thuc nguoi dung neu dung la co trong database se tra ve
        // thanh cong neu khong se nem ra ngoai le

        return jwtTokenUtils.generateToken(userExist);
    }


    /**
     * kiem tra numberphone da ton tai hay chua
     * @param phoneNumber so dien thoai nguoi dung nhap vao
     * @return tra ve true neu tai khoan nay ton tai, nguoc lai la false
     */
    @Override
    public boolean checkNumberphone(String phoneNumber) {
        if (userRepo.existsByNumberphone(phoneNumber)) {
            return true;
        }
        return false;
    }

    /**
     * kiem tra email da ton tai trong database  chua
     * @param email email cua nguoi dung nhap vao
     * @return tra ve true neu nhu eamil nay ton tai trong he thong, nguoc lai tra ve false
     */
    @Override
    public boolean exitsEmail(String email) {
        if (userRepo.existsByEmail(email)) {
            return true;
        }
        return false;
    }

    /**
     * kiem tra tai khoan cua user da cap nhat lai sau 30 ngay chua
     * @param users thong tin doi tuong user muon cap nhat mat khau
     * @return true neu mat khau chua cap nhat sau 30 ngay, false neu tai khoan duoc cap nhat
     */
    @Override
    public boolean isPasswordExpired(Users users) {
        LocalDateTime lastUpdate = users.getUpdatedAt();
        if (lastUpdate == null) {
            return false; // maajt khau chua tung cap nhat
        }
        return ChronoUnit.DAYS.between(lastUpdate, LocalDateTime.now()) > 30;
    }

    /**
     * cap nhat lai mat khau moi cho tai khoan va trar ve 1 token moi
     * @param users thong tin doi tuong user muon cap nhat lai
     * @return token cho tai khoan moi
     */
    @Override
    public String updatePassword(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setUpdatedAt(LocalDateTime.now());
        userRepo.save(users);
        return jwtTokenUtils.generateToken(users);
    }

    /**
     * tim kiem username ton tai trong h
     * @param username username nguoi dung nhap vao
     * @return doi tuong Users
     */
    @Override
    public Optional<Users> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    /**
     * kiem tra username ton tai trong he thong
     * @param username username nguoi dung nhap vao
     * @return true neu ton tai false  neu khong ton tai
     */
    @Override
    public boolean checkUsername(String username) {
        if (userRepo.existsByUsername(username)) {
            return true;
        }
        return false;
    }


    /**
     * chuyen doi tu 1 lop class sang lop class DTO de tranh gay mat mat du lieu
     * @param users ten tai khoan nguoi dung
     * @return tra ve 1 doi tuong dto
     */
    public UserDTO ConverDTO (Users users) {
        return modelMapper.map(users, UserDTO.class);
    }

    @Override
    public Users findByPhone(String phone) {
        return userRepo.findByNumberphone(phone).get();
    }


}
