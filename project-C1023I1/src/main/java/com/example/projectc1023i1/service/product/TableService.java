package com.example.projectc1023i1.service.product;

import com.example.projectc1023i1.model.product.Table;

import java.util.List;
import java.util.Optional;

public interface TableService {
    List<Table> findAll();             // Lấy tất cả các bàn
    Table findById(int id);            // Tìm bàn theo ID
    void save(Table table);            // Lưu bàn mới hoặc cập nhật bàn
    void deleteById(int id);           // Xóa bàn theo ID
}

