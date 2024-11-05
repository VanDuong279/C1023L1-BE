package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.Dto.product.income.IncomeDTO;
import com.example.projectc1023i1.model.product.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByOrderId(Integer id);
    List<Order> findByDayCreate(LocalDateTime dayCreate);

    @Query("SELECT SUM(o.totalMoneyOrder) FROM Order o WHERE o.dayCreate BETWEEN :from AND :to")
    Double sumTotalByDate(LocalDateTime from, LocalDateTime to);
    @Query(value = "SELECT HOUR(o.day_create) AS period, SUM(o.total_money_order) AS totalIncome " +
            "FROM `order` o " +
            "WHERE DATE(o.day_create) = CURRENT_DATE " +
            "GROUP BY HOUR(o.day_create)",
            nativeQuery = true)
    List<Object[]> getIncomeByHourToday();

    @Query(value = "SELECT DAY(o.day_create) AS period, SUM(o.total_money_order) AS totalIncome " +
            "FROM `order` o " +
            "WHERE MONTH(o.day_create) = MONTH(CURRENT_DATE) AND YEAR(o.day_create) = YEAR(CURRENT_DATE) " +
            "GROUP BY DAY(o.day_create)",
            nativeQuery = true)
    List<Object[]> getIncomeByDayInMonth();

    @Query(value = "SELECT DAYOFWEEK(o.day_create) AS period, SUM(o.total_money_order) AS totalIncome " +
            "FROM `order` o " +
            "WHERE YEARWEEK(o.day_create, 1) = YEARWEEK(CURRENT_DATE, 1) " +
            "GROUP BY DAYOFWEEK(o.day_create)",
            nativeQuery = true)
    List<Object[]> getIncomeByDayInWeek();

    @Query(value = "SELECT MONTH(o.day_create) AS period, SUM(o.total_money_order) AS totalIncome " +
            "FROM `order` o " +
            "WHERE YEAR(o.day_create) = YEAR(CURRENT_DATE) " +
            "GROUP BY MONTH(o.day_create)",
            nativeQuery = true)
    List<Object[]> getIncomeByMonthInYear();
    @Query(value = "SELECT SUM(o.totalMoneyOrder) FROM Order o WHERE o.dayCreate BETWEEN :from AND :to")
    List<Object[]> getIncomeByDateRange(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);



}
