package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.Category;
import com.example.projectc1023i1.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductsByProductNameContaining(String productName);
    List<Product> findProductsByCategory(Category category);
    Product findProductByProductCode(String productCode);
}
