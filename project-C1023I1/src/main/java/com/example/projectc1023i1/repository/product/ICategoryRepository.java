package com.example.projectc1023i1.repository.product;
import com.example.projectc1023i1.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsCategoryByCategoryName(String categoryName);
}
