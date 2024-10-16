package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
