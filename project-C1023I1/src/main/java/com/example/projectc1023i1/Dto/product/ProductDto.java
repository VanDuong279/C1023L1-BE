    package com.example.projectc1023i1.Dto.product;
    import com.example.projectc1023i1.model.product.Category;
    import com.example.projectc1023i1.model.product.Product;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import org.springframework.validation.Errors;
    import org.springframework.validation.Validator;
    import java.time.LocalDateTime;
    import java.util.List;



    @Getter
    @Setter
    public class ProductDto implements Validator {
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
        private boolean isUpdate = false;
        private List<Product> productList;


        @Override
        public boolean supports(Class<?> clazz) {
            return ProductDto.class.equals(clazz); // Sửa trả về true khi ProductDto được kiểm tra
        }

        @Override
        public void validate(Object target, Errors errors) {
            ProductDto productDto = (ProductDto) target;

            // Kiểm tra productName
            if (productDto.getProductName() == null || productDto.getProductName().trim().isEmpty()) {
                errors.rejectValue("productName", null, "Product name must not be empty");
            }
            // Kiểm tra tính duy nhất trong danh sách productList
            if (!productDto.isUpdate()){
                boolean isNameExisted = productDto.getProductList().stream()
                        .anyMatch(product -> product.getProductName().equalsIgnoreCase(productDto.getProductName()));
                if (isNameExisted) {
                    errors.rejectValue("productName", null, "Product name already existed");
                }
            }

            // Kiểm tra productPrice
            if (productDto.getProductPrice() <= 0) {
                errors.rejectValue("productPrice", null, "Price must be greater than 0 VND");
            }

            // Kiểm tra productCode
            if (productDto.getProductCode() == null || !productDto.getProductCode().matches("^PR-\\d+$")) {
                errors.rejectValue("productCode", null, "Product code must follow the format PR-X");

            }
        }


    public ProductDto() {
    }
}
