package com.example.projectc1023i1.Dto;

import com.example.projectc1023i1.Validation.SalaryMultiple;
import com.example.projectc1023i1.Validation.UniqueUsername;
import lombok.*;
import org.springframework.validation.Errors;
import jakarta.validation.constraints.*;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO extends UserDTO {

    @NotNull(message = "Mức lương không được để trống")
    @Positive(message = "Lương phải lớn hơn 0")
    @SalaryMultiple // Kiểm tra bội số của 100.000
    private Double salary;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(max = 100, message = "Địa chỉ không được vượt quá 100 ký tự")
    private String address;
    private Boolean isActive;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 6, message = "Tên đăng nhập phải lớn hơn 6 ký tự")
    @Pattern(regexp = "^(?!admin|root)[a-zA-Z][a-zA-Z0-9]*$",
            message = "Tên đăng nhập không được là 'admin', 'root', không được bắt đầu bằng số và chỉ chứa chữ và số")
    @UniqueUsername // Annotation kiểm tra tên đăng nhập duy nhất
    private String userName;

    @Override
    public void validate(Object target, Errors errors) {
        // Gọi validate của lớp cha (UserDTO)
        super.validate(target, errors);

        // Thêm validate cho các thuộc tính của EmployeeDTO
        EmployeeDTO employeeDTO = (EmployeeDTO) target;

        if (employeeDTO.getSalary() != null && employeeDTO.getSalary() <= 0) {
            errors.rejectValue("salary", "salary.positive", "Lương phải lớn hơn 0");
        }
    }
}
