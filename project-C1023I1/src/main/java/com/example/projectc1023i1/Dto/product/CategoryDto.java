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
public class CategoryDto implements Validator {
    @NotBlank(message = "categoryCode must be available")
    private String categoryCode;
    @Size(min = 3, message = "Category name must be at least 3 characters")
    private String categoryName;
    private String categoryImgUrl;

    private ICategoryService categoryService;

    @Autowired
    public void setCategoryService(ICategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryDto categoryDto = (CategoryDto) target;
        // Kiểm tra name
        if (categoryDto.getCategoryName().equals("")){
            errors.rejectValue("categoryName", null, "Not empty");
        } else {
            // Kiểm tra tính duy nhất
            if (categoryService != null && categoryService.existByCategoryName(categoryDto.getCategoryName())){
                errors.rejectValue("categoryName", null, "Category name already existed");
            }
        }
        if (!categoryDto.getCategoryCode().matches("^C-\\d+$")){
            errors.rejectValue("categoryCode", null, "Follow form C-X");
        }
    }
}
