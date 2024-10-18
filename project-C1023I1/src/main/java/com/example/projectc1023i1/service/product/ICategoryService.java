package com.example.projectc1023i1.service.product;
import com.example.projectc1023i1.model.product.Category;
import java.util.List;

public interface ICategoryService {
    List<Category> findAllCategories();
    Category findCategoryById(int id);
    void saveCategory(Category category);
    void deleteCategory(int id);
    boolean existByCategoryName(String categoryName);
}
