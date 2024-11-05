package com.example.projectc1023i1.controller.product;
import com.example.projectc1023i1.Dto.product.CategoryDto;
import com.example.projectc1023i1.model.product.Category;
import com.example.projectc1023i1.model.product.Product;
import com.example.projectc1023i1.service.product.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
//@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
   private ICategoryService categoryService;
    /**
     * Kiểm tra tên loại sa phẩm có tồn tại hay không
     */
    @GetMapping("/checkCategoryName")
    public ResponseEntity<Boolean> checkProductName(@RequestParam String categoryName) {
        boolean exists = categoryService.existByCategoryName(categoryName);
        return ResponseEntity.ok(exists);
    }

    /**
     * Hiển thị tất category
     * @return category's list
     */
    @GetMapping("")
    public ResponseEntity<List<Category>> getListCategory(){
        List<Category> categoryList = categoryService.findAllCategories();
        if (categoryList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
    /**
     * Hiển thị chi tiết Category
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable int id){
        Category category = categoryService.findCategoryById(id);
        if (category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    /** e
     * Thêm mới category
     */
    @PostMapping("")
    public ResponseEntity<Object> addCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult bindingResult){
        categoryDto.setCategoryList(categoryService.findAllCategories());
        new CategoryDto().validate(categoryDto, bindingResult);
        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        categoryService.saveCategory(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    /**
     * Chỉnh sửa category
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Object> editCategory(@PathVariable int id, @Valid @RequestBody CategoryDto categoryDto, BindingResult bindingResult){
        categoryDto.setUpdate(true);
        new CategoryDto().validate(categoryDto, bindingResult);
        Category existCategory = categoryService.findCategoryById(id);
        if (existCategory == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(categoryDto, existCategory);
        categoryService.saveCategory(existCategory);
        return new ResponseEntity<>(existCategory, HttpStatus.OK);
    }
    /**
     * Xoá category
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id){
        Category category = categoryService.findCategoryById(id);
        if (category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/hello")
    public ResponseEntity<?> hello () {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
