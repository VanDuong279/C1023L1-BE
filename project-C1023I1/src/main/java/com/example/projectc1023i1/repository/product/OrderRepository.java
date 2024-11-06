package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.Dto.product.income.IncomeDTO;
import com.example.projectc1023i1.model.product.Order;
import com.example.projectc1023i1.model.product.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderDetails,Integer> {
//    List<Order> findByOrderId(Integer id);
    List<OrderDetails> findByDayCreate(LocalDateTime dayCreate);

    @Query("SELECT SUM(od.totalMoneyOrder) FROM OrderDetails od WHERE od.dayCreate BETWEEN :from AND :to")
    Double sumTotalByDate(LocalDateTime from, LocalDateTime to);

    @Query(value = "SELECT HOUR(od.day_create) AS period, SUM(od.total_money_order) AS totalIncome " +
            "FROM order_details od " +
            "WHERE DATE(od.day_create) = CURRENT_DATE " +
            "GROUP BY HOUR(od.day_create)",
            nativeQuery = true)
    List<Object[]> getIncomeByHourToday();

    @Query(value = "SELECT DAY(od.day_create) AS period, SUM(od.total_money_order) AS totalIncome " +
            "FROM order_details od " +
            "WHERE MONTH(od.day_create) = MONTH(CURRENT_DATE) AND YEAR(od.day_create) = YEAR(CURRENT_DATE) " +
            "GROUP BY DAY(od.day_create)",
            nativeQuery = true)
    List<Object[]> getIncomeByDayInMonth();

    @Query(value = "SELECT DAYOFWEEK(od.day_create) AS period, SUM(od.total_money_order) AS totalIncome " +
            "FROM order_details od " +
            "WHERE YEARWEEK(od.day_create, 1) = YEARWEEK(CURRENT_DATE, 1) " +
            "GROUP BY DAYOFWEEK(od.day_create)",
            nativeQuery = true)
    List<Object[]> getIncomeByDayInWeek();

    @Query(value = "SELECT MONTH(od.day_create) AS period, SUM(od.total_money_order) AS totalIncome " +
            "FROM order_details od " +
            "WHERE YEAR(od.day_create) = YEAR(CURRENT_DATE) " +
            "GROUP BY MONTH(od.day_create)",
            nativeQuery = true)
    List<Object[]> getIncomeByMonthInYear();
    @Query(value = """
    SELECT 
        CASE 
            WHEN DATEDIFF(:toDate, :fromDate) = 0 THEN HOUR(od.day_create)
            WHEN DATEDIFF(:toDate, :fromDate) BETWEEN 2 AND 31 THEN DAY(od.day_create)
            WHEN DATEDIFF(:toDate, :fromDate) BETWEEN 32 AND 366 THEN MONTH(od.day_create)
            ELSE YEAR(od.day_create)
        END AS period,
        SUM(od.total_money_order) AS totalIncome
    FROM order_details od
    WHERE od.day_create BETWEEN :fromDate AND :toDate
    GROUP BY 
        CASE 
            WHEN DATEDIFF(:toDate, :fromDate) = 0 THEN HOUR(od.day_create)
            WHEN DATEDIFF(:toDate, :fromDate) BETWEEN 2 AND 31 THEN DAY(od.day_create)
            WHEN DATEDIFF(:toDate, :fromDate) BETWEEN 32 AND 366 THEN MONTH(od.day_create)
            ELSE YEAR(od.day_create)
        END
    """, nativeQuery = true)
    List<Object[]> getIncomeByRange(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);

    @Query(value = "SELECT SUM(od.totalMoneyOrder) FROM OrderDetails od WHERE od.dayCreate BETWEEN :from AND :to")
    List<Object[]> getIncomeByDateRange(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}