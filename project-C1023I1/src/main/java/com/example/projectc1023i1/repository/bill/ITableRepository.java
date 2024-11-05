package com.example.projectc1023i1.repository.bill;


import com.example.projectc1023i1.model.product.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITableRepository extends JpaRepository<Table, Long> {
    // Các phương thức tùy chỉnh nếu cần
}
