package com.example.projectc1023i1.controller;

import com.example.projectc1023i1.Dto.SendCodeDTO;
import com.example.projectc1023i1.Dto.UserDTO;
import com.example.projectc1023i1.Dto.UserLoginDTO;
import com.example.projectc1023i1.config.ModelMapperConfig;
import com.example.projectc1023i1.model.Users;
import com.example.projectc1023i1.respone.ChangePasswordRespone;
import com.example.projectc1023i1.respone.LoginRespone;
import com.example.projectc1023i1.respone.UserRespone;
import com.example.projectc1023i1.service.user.ISendCodeService;
import com.example.projectc1023i1.service.user.IUserService;
import com.example.projectc1023i1.service.user.impl.EmailSenderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private IUserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ISendCodeService sendCodeService;


    /**
     * Xử lý yêu cầu đăng nhập của người dùng.
     *
     * @param userLoginDTO Đối tượng chứa thông tin đăng nhập của người dùng, bao gồm tên tài khoản và mật khẩu.
     * @param bindingResult Chứa kết quả của quá trình validate và ràng buộc dữ liệu từ form đăng nhập.
     * @return Trả về ResponseEntity với các trạng thái sau:
     *         - BadRequest (400) nếu như mật khẩu đã hết hạn quá 30 ngày.
     *         - BadRequest (400) nếu dữ liệu đăng nhập không hợp lệ (validate thất bại).
     *         - NotFound (404) nếu không tìm thấy tên tài khoản trong hệ thống.
     *         - Ok (200) nếu đăng nhập thành công và trả về thông tin người dùng cùng token.
     *         - InternalServerError (500) nếu có lỗi xảy ra trong quá trình xử lý.
     */
    @PostMapping( value = "/login", produces = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO,
                                   BindingResult bindingResult) {
        try {
            if(bindingResult.hasErrors()){
                List<String> errorsMesssage = bindingResult.getFieldErrors().
                        stream().
                        map(FieldError:: getDefaultMessage)
                        .toList();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMesssage);
            }
            Optional<Users> optionalUser = userService.findByUsername(userLoginDTO.getUsername());
            if (!optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay nguoi dung");
            }
            Users users = optionalUser.get();
            String token =   userService.login(userLoginDTO.getUsername(),userLoginDTO.getPassword());
            if (userService.isPasswordExpired(users)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ChangePasswordRespone.builder()
                        .message("Mat khau ban da qua han 30 ngay can thay doi lai mat khau")
                        .oldPassword(users.getPassword())
                        .username(userLoginDTO.getUsername())
                        .build());
            }
            return ResponseEntity.ok().body(LoginRespone.builder()
                    .token(token)
                    .message("Ban da dang nhap thanh cong")
                    .userDTO(userService.ConverDTO(users))
                    .build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * dùng để thay đổi password
     * @param username tên tài khoản của người dùng
     * @param passwordChange mật khẩu thay đổi
     * @return Ok (200) nếu thay đổi mật khẩu thành công
     */
    @PostMapping("/user/change-password")
    public ResponseEntity<?> changePassword(
            @RequestParam("username") String username,
            @RequestParam("passwordChange") String passwordChange
    ) {
        Users users = userService.findByUsername(username).get();
        if (users ==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Khong tim thay nguoi dung");
        }
        users.setPassword(passwordChange);
        String token = userService.updatePassword(users);
        return ResponseEntity.ok(UserRespone.builder()
                .message("da cap nhat mat khau thanh cong")
                .userDTO(userService.ConverDTO(users))
                .token(token)
                .build());
    }


    /**
     *  dùng để tạo ra 1 tài khoản user
     * @param userDTO chứa thông tin của người của người dùng tạo tài khoản
     * @param bindingResult chứa kết quả của quá trình validate và ràng buộc dữ liệu
     * @param request là đối tượng HttpServletRequest được gửi từ đối tượng Client
     * @return  trả về (400) nếu quá trình validate xảy ra lỗi
     *          trả về (200) nếu như quá trình validate thành công và xử lý dữ liệu thành công trong database
     */
    @PostMapping("/user/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO,
                                        BindingResult bindingResult,
                                        HttpServletRequest request) {
        try {
            if(bindingResult.hasErrors()){
                List<String> errorsMesssage = bindingResult.getFieldErrors().
                        stream().
                        map(FieldError:: getDefaultMessage)
                        .toList();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMesssage);
            }

            if (userService.exitsEmail(userDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tai khoan email nay da ton tai");
            }else if (userService.exitsUsername(userDTO.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tai khoan  nay da ton tai");
            }else if (userService.exitsNumberphone(userDTO.getNumberphone())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("So dien thoai nay da ton tai");
            }

            userService.createUser(userDTO);
            return ResponseEntity.ok().body(UserRespone.builder()
                    .userDTO(userDTO)
                    .message("Ban da tao thanh cong 1 user")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * luw
     * @param email email cua nguoi dung nhap vao de check
     * @return tra ve (500) neu xay ra trong qua trinh xu ly
     *         tra ve (404) neu khong tim thay thong tin nguoi dung
     *         tra ve (200) neu da luu vao trong database bang check-email
     */
    @PostMapping("/email/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam("email") String email) {
        if (!userService.exitsEmail(email)) {
            Integer code = emailSenderService.sendSimpleMail(email);
            if (code!=0) {
                String checkCode = String.valueOf(code);
                sendCodeService.save(new SendCodeDTO(checkCode,email));
                return ResponseEntity.ok("da hoan thanh");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap("errors",false));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("nguoi dung  da ton tai trong he thong");

    }


    /**
     *  gui ma code den email nguoi tao tai khoan
     * @param userDTO chứa thông tin của người của người dùng tạo tài khoản
     * @param bindingResult chứa kết quả của quá trình validate và ràng buộc dữ liệu
     * @return tra ve (400) neu qua trinh validate du lieu xay ra sai va qua trinh kiem tra du lieu
     *         tra ve (400) neu nhu tai khoan da ton tai
     *         tra ve (400) neu nhu numberphone da ton tai
     *         tra ve (400) neu nhu email da ton tai
     *         tra ve (500) nem ra ngoai le neu xay ra loi trong qua trinh xu ly du lieu
     *         tra ve (200) neu nhu qua trinh gui code qua email thanh cong
     */
    @PostMapping("/email/send-code-email")
    public ResponseEntity<?> sendCodeEmail(@Valid @RequestBody UserDTO userDTO,
                                           BindingResult bindingResult) {
        try {
            if(bindingResult.hasErrors()){
                List<String> errorsMesssage = bindingResult.getFieldErrors().
                        stream().
                        map(FieldError:: getDefaultMessage)
                        .toList();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("errors",errorsMesssage));
            }
            Integer code = emailSenderService.sendSimpleMail(userDTO.getEmail());
            if (code!=0) {
                String checkCode = String.valueOf(code);
                sendCodeService.save(new SendCodeDTO(checkCode,userDTO.getEmail()));
                return ResponseEntity.ok("ddax hoan thanh");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap("errors",false));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error",false));
        }
    }

    /**
     *  dung de kiem tra ma code nguoi dung nhap vao
     * @param code ma code nguoi dung nhap vao sau khi nhan ma code duojc gui tu he thong den mail nguoi dung
     * @param email đây là email người dùng dùng để đăng kí tài khoản
     * @return tra ve (400) neu ma code khong ton tai
     *         tra ve (500) nem ra 1 ngoai le neu nhu xay ra troong qua trinh xu ly du lieu
     *         tra ve (200) nếu như dữ liệu trong bảng SendCode này xóa đi
     */
    @PostMapping("/email/check-code")
    public ResponseEntity<?> checkCode(@RequestParam("code") String code,
                                       @RequestParam("email") String email) {
        try {
            // Tìm session dựa trên email
            String sessionCheck = sendCodeService.findByEmail(email);


            // Kiểm tra nếu mã code không tồn tại
            if (sessionCheck == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã code không tồn tại");
            }

            // So sánh mã code và xóa session nếu khớp
            if (sessionCheck.equals(code)) {
                sendCodeService.delete(email);
            }

            // Trả về trạng thái OK nếu mọi thứ đều thành công
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","Da kiem tra thanh cong"));

        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về lỗi nội bộ server
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi trong quá trình kiểm tra mã code");
        }
    }



    @PostMapping("/username-exits-check")
    public ResponseEntity<?> findAccountByUsername(@RequestParam("username") String username) {
        try {
            if (userService.exitsUsername(username)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usernameExist");
            }
            return ResponseEntity.ok().body("Let's continue");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ban chua nhap so dien thoai");
        }
    }

    @PostMapping("/email-exits-check/{id}")
    public ResponseEntity<?> findAccountByEmail(@RequestParam("email") String email) {
        try {
            if (userService.exitsEmail(email)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("emailExist ");
            }
            return ResponseEntity.ok().body("Let's continue ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ban chua nhap so dien thoai");
        }
    }

    @PostMapping("/numberphone-exits-check")
    public ResponseEntity<?> findAccountByNumberPhone(@RequestParam("numberphone") String numberphone) {
        try {
            if (userService.exitsNumberphone(numberphone)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("numberphoneExist ");
            }
            return ResponseEntity.ok().body("Lets continue ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ban chua nhap so dien thoai");
        }
    }


    @GetMapping("/user/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        if (userService.exitsEmail(email)) {
            Integer code = emailSenderService.sendSimpleMail(email);
            if (code!=0) {
                String checkCode = String.valueOf(code);
                sendCodeService.save(new SendCodeDTO(checkCode,email));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay email");
    }

    @PostMapping("/storage/hello/{id}")
    public ResponseEntity<?> hello(@Valid @RequestParam("id") Integer id) {
        int a = 10;
        return ResponseEntity.status(HttpStatus.OK).body("hello");
    }
}
