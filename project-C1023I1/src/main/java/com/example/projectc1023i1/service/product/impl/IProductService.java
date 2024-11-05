package com.example.projectc1023i1.service.product.impl;

import com.example.projectc1023i1.model.product.Product;
import com.example.projectc1023i1.repository.product.ProductRepository;
import com.example.projectc1023i1.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IProductService implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findProductByCategory(int categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId,pageable);
    }
}
