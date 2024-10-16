package com.example.projectc1023i1.Dto;

import com.example.projectc1023i1.Validation.SalaryMultiple;
import com.example.projectc1023i1.Validation.UniqueUsername;
import lombok.*;
import org.springframework.validation.Validator;
import jakarta.validation.constraints.*;

import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO extends UserDTO implements Validator {

    @NotNull(message = "Mức lương không được để trống")
    @Positive(message = "Lương phải lớn hơn 0")
    @SalaryMultiple // Kiểm tra bội số của 100.000
    private Double salary;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;



    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 6, message = "Tên đăng nhập phải lớn hơn 6 ký tự")
    @Pattern(regexp = "^(?!admin|root)[a-zA-Z][a-zA-Z0-9]*$",
            message = "Tên đăng nhập không được là 'admin', 'root', không được bắt đầu bằng số và chỉ chứa chữ và số")
    @UniqueUsername // Annotation kiểm tra tên đăng nhập duy nhất
    private String userName;

}
