package com.example.projectc1023i1.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Validator {


    private  String url;

    @NotBlank(message = "Khonng duoc de trong ten")
    @Size(max = 50,message = "khong duoc qua 50 ki tu")
    private String fullName;

    private String address;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    @NotBlank(message = "so dien thoai khong duoc de trong ")
    @Pattern(regexp = "^(03|05|07|08|09)\\d{8}$", message = "So dien thoai khong dung dinh dang ")
    private String numberphone;

    @NotBlank(message = "Khong duoc de trong")
    @Size(min = 6, message = "Tên đăng nhập phải lớn hơn 6 ký tự")
    private String username;

    @NotBlank(message = "Khong duoc de trong password")
    @Size(min = 8, message = "Mat khau phai co it nhat 8 ky tu")
    private String password;

    @NotBlank(message = "Khong duoc de trong email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email không hợp lệ")
    @Size(max = 50,message = "khong duoc qua 50 ki tu")
    private String email;

    private Double salary;

    private Boolean isActive;

    private Integer roleId;


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
    }

}
