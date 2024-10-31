package com.example.projectc1023i1.service.product;

import com.example.projectc1023i1.model.product.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TableService {
    List<Table> findAll();             // Lấy tất cả các bàn
    Table findById(int id);            // Tìm bàn theo ID
    void save(Table table);            // Lưu bàn mới hoặc cập nhật bàn
    void deleteById(int id);           // Xóa bàn theo ID

    Page<Table> findAllTable(Pageable pageable);
    Page<Table> findTableByCode(@Param("table_code") String tableCode,Pageable pageable);
    Page<Table> findTableByStatus(@Param("status") boolean status,Pageable pageable);
    boolean deleteTableById(@Param("tableId") int tableId);
    boolean updateTableById(@Param("tableId") int tableId, @Param("newStatus") boolean newStatus);
    void createTable(String tableCode,String tableName, boolean status);
}

