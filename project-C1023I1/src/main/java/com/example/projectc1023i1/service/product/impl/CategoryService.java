package com.example.projectc1023i1.service.product.impl;
import com.example.projectc1023i1.model.product.Category;
import com.example.projectc1023i1.repository.product.ICategoryRepository;
import com.example.projectc1023i1.service.product.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existByCategoryName(String categoryName) {
        return categoryRepository.existsCategoryByCategoryName(categoryName);
    }
}
