package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product WHERE category_id = :categoryId", nativeQuery = true)
    Page<Product> findByCategoryId(@Param("categoryId") int categoryId, Pageable pageable);
}
