package com.example.projectc1023i1.service.product.impl;


import com.example.projectc1023i1.model.product.OrderDetails;
import com.example.projectc1023i1.repository.product.OrderDetailsRepository;
import com.example.projectc1023i1.service.product.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Override
    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public List<OrderDetails> findOrdersByTableId(int tableId) {
        return orderDetailsRepository.findOrderDetailsByTableId(tableId);
    }


    @Override
    public void save(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }
    @Override
    public void delete(OrderDetails orderDetails) {
        orderDetailsRepository.delete(orderDetails);
    }

    @Override
    public OrderDetails findById(int id) {
        return orderDetailsRepository.findById(id).orElse(null);
    }
}
