package com.example.projectc1023i1.service.product.impl;


import com.example.projectc1023i1.model.product.Table;
import com.example.projectc1023i1.repository.product.TableRepository;
import com.example.projectc1023i1.service.product.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Override
    public List<Table> findAll() {
        return tableRepository.findAll();
    }

    @Override
    public Table findById(int id) {
        Optional<Table> table = tableRepository.findById(id);
        return table.orElse(null);  // Trả về null nếu không tìm thấy
    }

    @Override
    public void save(Table table) {
        tableRepository.save(table);
    }

    @Override
    public void deleteById(int id) {
        tableRepository.deleteById(id);
    }


    @Override
    public List<Table> findAllTable() {
        return tableRepository.findAllTable();
    }

    @Override
    public Table findTableByCode(String tableCode) {
        return tableRepository.findTableByCode(tableCode);
    }

    @Override
    public List<Table> findTableByStatus(boolean status) {
        return tableRepository.findTableByStatus(status);
    }

    @Override
    public boolean deleteTableById(int tableId) {
        return tableRepository.deleteTableById(tableId);
    }

    @Override
    public void updateTableById(Long tableId, boolean newStatus) {
        tableRepository.updateTableById(tableId,newStatus);
    }
}
