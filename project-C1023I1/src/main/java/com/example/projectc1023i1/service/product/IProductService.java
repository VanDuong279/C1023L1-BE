package com.example.projectc1023i1.service.product;

import com.example.projectc1023i1.model.product.Category;
import com.example.projectc1023i1.model.product.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();
    Product findProductById(int id);
    List<Product> searchByName(String productName);
    Product searchByCode(String productCode);
    List<Product> searchByCategory(Category category);
    void saveProduct(Product product);
    void deleteProduct(int id);
}
