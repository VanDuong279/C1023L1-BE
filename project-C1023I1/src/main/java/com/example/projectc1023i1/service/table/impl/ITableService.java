package com.example.projectc1023i1.service.table.impl;

import com.example.projectc1023i1.model.Table;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITableService {
    List<Table> findAllTable();
    Table findTableByCode(@Param("table_code") String tableCode);
    List<Table> findTableByStatus(@Param("status") boolean status);
    boolean deleteTableById(@Param("tableId") int tableId);
    void updateTableById(@Param("tableId") Long tableId, @Param("newStatus") boolean newStatus);
}
