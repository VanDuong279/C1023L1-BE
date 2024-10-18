package com.example.projectc1023i1.Dto.product;

import com.example.projectc1023i1.model.product.Category;
import jakarta.validation.Validation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class UpdateProductDto implements Validator {
    @NotBlank(message = "productCode must be available")
    private String productCode;

    @Size(min = 3, message = "Product name must be at least 3 characters")
    private String productName;

    private double productPrice;

    private String productImgUrl;

    private boolean productStatus = true;

    private LocalDateTime createDay;

    private LocalDateTime updateDay;

    private Category category;

    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateProductDto.class.equals(clazz); // Sửa trả về true khi UpdateProductDto được kiểm tra
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdateProductDto updateProductDto = (UpdateProductDto) target;
        // Kiểm tra productName
        if (updateProductDto.getProductName() == null || updateProductDto.getProductName().trim().isEmpty()) {
            errors.rejectValue("productName", null, "Product name must not be empty");
        }


        // Kiểm tra productPrice
        if (updateProductDto.getProductPrice() <= 0) {
            errors.rejectValue("productPrice", null, "Price must be greater than 0 VND");
        }

        // Kiểm tra productCode
        if (updateProductDto.getProductCode() == null || !updateProductDto.getProductCode().matches("^PR-\\d+$")) {
            errors.rejectValue("productCode", null, "Product code must follow the format PR-X");
        }
    }
}
