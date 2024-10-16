    package com.example.projectc1023i1.Dto.product;

    import com.example.projectc1023i1.model.product.Category;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import org.springframework.validation.Errors;
    import org.springframework.validation.Validator;
    import java.time.LocalDateTime;
    @NoArgsConstructor
    @Getter
    @Setter
    public class ProductDto implements Validator {
        @NotBlank(message = "productCode must be available")
        private String productCode;
        @Size(min = 1, max = 225, message = "Product name must be between 1 and 225 characters")
        private String productName;
        private double productPrice;
        private String productImgUrl;
        private boolean productStatus = true;
        private LocalDateTime createDay;
        private LocalDateTime updateDay;
        private Category category;


        @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

        @Override
        public void validate(Object target, Errors errors) {
            ProductDto productDto = (ProductDto) target;
            if (productDto.getProductName().equals("")){
                errors.rejectValue("productName", null, "Not empty");
            }
            if (productDto.getProductPrice() == -1.0){
                errors.rejectValue("productPrice", null, "Price is required");
            } else if (productDto.getProductPrice() <= 0){
                errors.rejectValue("productPrice", null, "Price can not less than 0 VND");
            }
            if (!productDto.productCode.matches("^PR-\\d+$")){
                errors.rejectValue("productCode", null, "Follow form PR-X");
            }
        }
    }
