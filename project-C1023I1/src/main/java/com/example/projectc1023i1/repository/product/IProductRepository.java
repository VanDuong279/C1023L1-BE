package com.example.projectc1023i1.repository.product;
import com.example.projectc1023i1.model.product.Category;
import com.example.projectc1023i1.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT COUNT(*) > 0 FROM product WHERE product_name = :productName", nativeQuery = true)
    boolean existsProductByExactProductName(@Param("productName") String productName);
    boolean existsProductByProductName(String productName);
    Page<Product> findAll(Pageable pageable);
    List<Product> findProductsByProductNameContaining(String productName);
    Page<Product> findProductsByProductNameContaining(String productName, Pageable pageable);
    List<Product> findProductsByCategory(Category category);
    Page<Product> findProductsByCategory(Category category, Pageable pageable);
    Product findProductByProductCode(String productCode);
    @Query("SELECT MAX(CAST(SUBSTRING(p.productCode, 4) AS integer)) FROM Product p WHERE p.productCode LIKE 'PR-%'")
    Integer findMaxProductCode();
}
