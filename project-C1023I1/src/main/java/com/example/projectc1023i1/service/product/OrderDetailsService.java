package com.example.projectc1023i1.service.product;

import com.example.projectc1023i1.model.product.OrderDetails;

import java.util.List;

public interface OrderDetailsService {
    List<OrderDetails> findAll();
    List<OrderDetails> findOrdersByTableId(int tableId); // Lấy danh sách đơn hàng theo bàn
    void save(OrderDetails orderDetails);

    void delete(OrderDetails orderDetails);

    OrderDetails findById(int id);
}
