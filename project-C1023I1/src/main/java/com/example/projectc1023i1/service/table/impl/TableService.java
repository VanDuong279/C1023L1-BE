package com.example.projectc1023i1.service.table.impl;

import com.example.projectc1023i1.model.Table;
import com.example.projectc1023i1.repository.ITableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService implements ITableService {
    @Autowired
    private ITableRepository tableRepository;

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
