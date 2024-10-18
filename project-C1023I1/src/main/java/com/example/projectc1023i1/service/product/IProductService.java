package com.example.projectc1023i1.service.product;

import com.example.projectc1023i1.model.product.Category;
import com.example.projectc1023i1.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<Product> findAllProducts(Pageable pageable);
    List<Product> findAllProducts();
    Product findProductById(int id);
    List<Product> searchByName(String productName);
    Page<Product> searchByName(String productName, Pageable pageable);
    Product searchByCode(String productCode);
    List<Product> searchByCategory(Category category);
    Page<Product> searchByCategory(Category category, Pageable pageable);
    void saveProduct(Product product);
    void deleteProduct(int id);
    boolean existProductName(String productName);
}
