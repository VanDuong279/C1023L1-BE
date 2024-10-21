package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.Order;
import com.example.projectc1023i1.model.product.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByTableId(int tableId);
    List<OrderDetails> findByOrder(Order order);
}
