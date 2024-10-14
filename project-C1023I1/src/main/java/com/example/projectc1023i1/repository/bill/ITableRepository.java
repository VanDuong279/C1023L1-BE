package com.example.projectc1023i1.repository.bill;


import com.example.projectc1023i1.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITableRepository extends JpaRepository<TableEntity, Long> {
    // Các phương thức tùy chỉnh nếu cần
}
