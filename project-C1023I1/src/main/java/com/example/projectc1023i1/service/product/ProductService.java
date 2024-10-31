package com.example.projectc1023i1.service.product;

import com.example.projectc1023i1.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface ProductService {
    Page<Product> findProductByCategory(int categoryId,Pageable pageable);
}
