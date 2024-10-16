package com.example.projectc1023i1.Dto.product;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto implements Validator {
    private String categoryCode;
    @Size(min = 1, max = 225, message = "Category name must be between 1 and 225 characters")
    private String categoryName;
    private String categoryImgUrl;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryDto categoryDto = (CategoryDto) target;
        if (categoryDto.getCategoryName().equals("")){
            errors.rejectValue("categoryName", null, "Not empty");
        }
    }
}
