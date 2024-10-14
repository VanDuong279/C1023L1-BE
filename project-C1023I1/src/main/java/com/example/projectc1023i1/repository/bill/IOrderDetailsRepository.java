package com.example.projectc1023i1.repository.bill;


import com.example.projectc1023i1.entity.Order;
import com.example.projectc1023i1.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByOrder(Order order);
}
