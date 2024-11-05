package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.Order;
import com.example.projectc1023i1.model.product.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByTableId(int tableId);
    List<OrderDetails> findByOrder(Order order);
    @Query("SELECT od FROM OrderDetails od WHERE od.order.orderId = :orderId")
    List<OrderDetails> findByOrderId(@Param("orderId") Integer orderId);

    @Query("SELECT SUM(od.totalMoneyOrder) FROM OrderDetails od WHERE od.order.orderId = :orderId")
    Double sumTotalMoneyOrderByOrderId(@Param("orderId") Integer orderId);

    @Query("SELECT SUM(od.quantity) FROM OrderDetails od WHERE od.order.orderId = :orderId")
    Integer sumQuantityByOrderId(@Param("orderId") Integer orderId);
    @Query(value = "SELECT * FROM order_details WHERE table_id = :tableId", nativeQuery = true)
    List<OrderDetails> findOrderDetailsByTableId(@Param("tableId") int tableId);


}
