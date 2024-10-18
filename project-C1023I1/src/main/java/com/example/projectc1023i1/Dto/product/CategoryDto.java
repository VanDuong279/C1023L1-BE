package com.example.projectc1023i1.Dto.product;
import com.example.projectc1023i1.model.product.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto implements Validator {
    @NotBlank(message = "categoryCode must be available")
    private String categoryCode;
    @Size(min = 3, message = "Category name must be at least 3 characters")
    private String categoryName;
    private String categoryImgUrl;
    private boolean isUpdate;
    private List<Category> categoryList;

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
        }

        // Kiểm tra tính duy nhât
        if (!categoryDto.isUpdate()){
            boolean isNameExisted = categoryDto.getCategoryList().stream()
                    .anyMatch(category -> category.getCategoryName().equalsIgnoreCase(categoryDto.getCategoryName()));
            if (isNameExisted){
                errors.rejectValue("categoryName", null, "Category name already existed");
            }
        }
        // Kiểm tra regex code

        if (!categoryDto.getCategoryCode().matches("^C-\\d+$")){
            errors.rejectValue("categoryCode", null, "Follow form C-X");
        }
    }
}
