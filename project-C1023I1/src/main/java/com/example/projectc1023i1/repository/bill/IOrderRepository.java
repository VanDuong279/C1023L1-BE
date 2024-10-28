package com.example.projectc1023i1.repository.bill;

import com.example.projectc1023i1.model.product.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderId(Integer id);
    List<Order> findByDayCreate(LocalDate dayCreate);

    @Query("SELECT SUM(o.totalMoneyOrder) FROM Order o WHERE o.dayCreate BETWEEN :from AND :to")
    BigDecimal sumTotalByDate(LocalDate from, LocalDate to);
}
