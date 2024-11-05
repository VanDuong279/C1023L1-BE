package com.example.projectc1023i1.controller.product;
import com.example.projectc1023i1.Dto.product.ProductDto;
import com.example.projectc1023i1.model.product.Category;
import com.example.projectc1023i1.model.product.Product;
import com.example.projectc1023i1.service.product.ICategoryService;
import com.example.projectc1023i1.service.product.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    /**
     * Kiểm tra tên sản phẩm có tồn tại hay không
     */
    @GetMapping("/checkProductName")
    public ResponseEntity<?> checkProductName(@RequestParam String productName) {
        System.out.println("-----------------------------" + productName);
        boolean exists = productService.existProductName(productName);
        System.out.println(exists + "aaaaa");
        if (exists) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
    /**
     * Hiển thị tất cả Product
     */
    @GetMapping("")
    public ResponseEntity<Page<Product>> getListProduct(@RequestParam(defaultValue = "0")int page,
                                                        @RequestParam(defaultValue = "10")int size,
                                                        @RequestParam(defaultValue = "productName") String sortBy,
                                                        @RequestParam(defaultValue = "asc") String sortOrder){
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size, sort);
        Page<Product> productPage = productService.findAllProducts(pageable);
        if (productPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
    /**
     * Hiển thị chi tiết Product
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable int id){
        Product product = productService.findProductById(id);
        if (product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    /**
     * Tìm kiếm theo productName
     */
    @GetMapping("/searchByProductName")
    public ResponseEntity<Page<Product>> searchByProductName(@RequestParam(required = false) String productName,
                                                             @RequestParam(defaultValue = "0")int page,
                                                             @RequestParam(defaultValue = "10")int size){
        Sort sort = Sort.by("productName").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productService.searchByName(productName, pageable);
        if (productPage.isEmpty()){
            return new ResponseEntity<>(productPage, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
    /**
     * Tìm kiếm theo productCode
     */
    @GetMapping("/searchByProductCode")
    public ResponseEntity<Object> searchByProductCode(@RequestParam(required = false) String productCode){
        Product product = productService.searchByCode(productCode);
        if (product == null){
            return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    /**
     * Tìm kiếm theo Category
     */
    @GetMapping("/searchByCategory")
    public ResponseEntity<Page<Product>> searchByCategory(@RequestParam int categoryId,
                                                          @RequestParam(defaultValue = "0")int page,
                                                          @RequestParam(defaultValue = "10")int size){
        Category category = categoryService.findCategoryById(categoryId);
        if (category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Sort sort = Sort.by(category.getCategoryName()).ascending();
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.searchByCategory(category, pageable);
        if (productPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    /**
     * Thêm mới Product
     */
    @PostMapping("")
    public ResponseEntity<Object> addProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult){
        productDto.setProductList(productService.findAllProducts());
       new ProductDto().validate(productDto, bindingResult);
        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        productDto.setProductCode(productService.generateNextProductCode());
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        productService.saveProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    /**
     * Chỉnh sửa Product
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Object> editProduct(@PathVariable int id, @Valid @RequestBody ProductDto productDto, BindingResult bindingResult){
        productDto.setUpdate(true);
        new ProductDto().validate(productDto, bindingResult);
        Product existProduct = productService.findProductById(id);
        if (existProduct == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(productDto, existProduct);
        productService.saveProduct(existProduct);
        return new ResponseEntity<>(existProduct, HttpStatus.OK);
    }
    /**
     * Xoá Product
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id){
        Product product = productService.findProductById(id);
        if (product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
