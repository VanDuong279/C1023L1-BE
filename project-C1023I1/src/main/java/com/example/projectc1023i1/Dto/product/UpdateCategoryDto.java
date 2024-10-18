package com.example.projectc1023i1.Dto.product;

import com.example.projectc1023i1.service.product.ICategoryService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@NoArgsConstructor
@Getter
@Setter
public class UpdateCategoryDto implements Validator {
    @NotBlank(message = "categoryCode must be available")
    private String categoryCode;
    @Size(min = 3, message = "Category name must be at least 3 characters")
    private String categoryName;
    private String categoryImgUrl;


    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateCategoryDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdateCategoryDto updateCategoryDto = (UpdateCategoryDto) target;
        // Kiểm tra name
        if (updateCategoryDto.getCategoryName().equals("")){
            errors.rejectValue("categoryName", null, "Not empty");
        }
        if (!updateCategoryDto.getCategoryCode().matches("^C-\\d+$")){
            errors.rejectValue("categoryCode", null, "Follow form C-X");
        }
    }
}
