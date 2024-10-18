package com.example.projectc1023i1.repository.product;
import com.example.projectc1023i1.model.product.Category;
import com.example.projectc1023i1.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    boolean existsProductByProductName(String productName);
    Page<Product> findAll(Pageable pageable);
    List<Product> findProductsByProductNameContaining(String productName);
    Page<Product> findProductsByProductNameContaining(String productName, Pageable pageable);
    List<Product> findProductsByCategory(Category category);
    Page<Product> findProductsByCategory(Category category, Pageable pageable);
    Product findProductByProductCode(String productCode);
}
