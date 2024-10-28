package com.example.projectc1023i1.service.product;

import com.example.projectc1023i1.model.product.Table;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TableService {
    List<Table> findAll();             // Lấy tất cả các bàn
    Table findById(int id);            // Tìm bàn theo ID
    void save(Table table);            // Lưu bàn mới hoặc cập nhật bàn
    void deleteById(int id);           // Xóa bàn theo ID

    List<Table> findAllTable();
    Table findTableByCode(@Param("table_code") String tableCode);
    List<Table> findTableByStatus(@Param("status") boolean status);
    boolean deleteTableById(@Param("tableId") int tableId);
    boolean updateTableById(@Param("tableId") Long tableId, @Param("newStatus") boolean newStatus);
    void createTable();
}

