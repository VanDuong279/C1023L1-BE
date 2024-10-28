package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByOrderId(Integer id);
    List<Order> findByDayCreate(LocalDateTime dayCreate);

    @Query("SELECT SUM(o.totalMoneyOrder) FROM Order o WHERE o.dayCreate BETWEEN :from AND :to")
    BigDecimal sumTotalByDate(LocalDateTime from, LocalDateTime to);
}
