package com.example.projectc1023i1.Dto.product;

import com.example.projectc1023i1.Validation.SalaryMultiple;
import com.example.projectc1023i1.Validation.UniqueUsername;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Tên nhân viên không được để trống")
    private String fullName;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String numberPhone;

    private String imgUrl;
    private Date birthday;
    private String email;
    @NotNull(message = "Mức lương không được để trống")
    @Positive(message = "Lương phải lớn hơn 0")
    @SalaryMultiple // Kiểm tra bội số của 100.000
    private Double salary;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
    private Boolean isActive;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 6, message = "Tên đăng nhập phải lớn hơn 6 ký tự")
    @Pattern(regexp = "^(?!admin|root)[a-zA-Z][a-zA-Z0-9]*$",
            message = "Tên đăng nhập không được là 'admin', 'root', không được bắt đầu bằng số và chỉ chứa chữ và số")
    @UniqueUsername // Annotation kiểm tra tên đăng nhập duy nhất
    private String userName;

    private String password;
    private Integer roleId;
}
