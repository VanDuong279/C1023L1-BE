package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
        @Query(value = "SELECT * FROM order_details WHERE table_id = :tableId", nativeQuery = true)
        List<OrderDetails> findOrderDetailsByTableId(@Param("tableId") int tableId);
}

