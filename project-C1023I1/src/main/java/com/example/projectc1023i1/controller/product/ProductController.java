package com.example.projectc1023i1.controller.product;

import com.example.projectc1023i1.Dto.product.ProductDto;
import com.example.projectc1023i1.model.product.Category;
import com.example.projectc1023i1.model.product.Product;
import com.example.projectc1023i1.service.product.ICategoryService;
import com.example.projectc1023i1.service.product.IProductService;
import jakarta.validation.Valid;
import lombok.Builder;
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

import java.security.DigestException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
//@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    /**
     * Hiển thị tất cả Product
     */
    @GetMapping("")
    public ResponseEntity<Page<Product>> getListProduct(@RequestParam(defaultValue = "0")int page,
                                                        @RequestParam(defaultValue = "10")int size){
        Sort sort = Sort.by("productName").ascending();
        Pageable pageable = PageRequest.of(size, page, sort);
//        List<Product> productList = productService.findAllProducts();
        Page<Product> productPage = productService.findAllProducts(pageable);
        if (productPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
    /**
     * Hiển thị chi tiết Product
     */
    @GetMapping("/detail/{id}")
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
//        List<Product> productList = productService.searchByName(productName);
        Page<Product> productPage = productService.searchByName(productName, pageable);
        if (productPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        Pageable pageable = PageRequest.of(page, size, sort);
//        List<Product> productList = productService.searchByCategory(category);
        Page<Product> productPage = productService.searchByCategory(category, pageable);
        if (productPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
    /**
     * Thêm mới Product
     */
    @PostMapping("/createProduct")
    public ResponseEntity<Object> addProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        productService.saveProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    /**
     * Chỉnh sửa Product
     */
    @PatchMapping("/editProduct/{id}")
    public ResponseEntity<Object> editProduct(@PathVariable int id, @Valid @RequestBody ProductDto productDto, BindingResult bindingResult){
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
    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id){
        Product product = productService.findProductById(id);
        if (product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
