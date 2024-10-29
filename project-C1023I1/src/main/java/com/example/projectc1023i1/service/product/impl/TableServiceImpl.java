package com.example.projectc1023i1.service.product.impl;


import com.example.projectc1023i1.model.product.Table;
import com.example.projectc1023i1.repository.product.TableRepository;
import com.example.projectc1023i1.service.product.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
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
    public Page<Table> findAllTable(Pageable pageable) {
        return tableRepository.findAllTable(pageable);
    }

    @Override
    public Page<Table> findTableByCode(String tableCode,Pageable pageable) {
        return tableRepository.findTableByCode(tableCode,pageable);
    }

    @Override
    public Page<Table> findTableByStatus(boolean status, Pageable pageable) {
        return tableRepository.findTableByStatus(status,pageable);
    }

    @Override
    public boolean deleteTableById(int tableId) {
        try {
            if (tableRepository.existsById(tableId)) {
                tableRepository.deleteById(tableId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateTableById(Long tableId, boolean newStatus) {
        try {
            tableRepository.updateTableById(tableId, newStatus);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    @Override
    public void createTable() {
           tableRepository.createTable();
    }


}
