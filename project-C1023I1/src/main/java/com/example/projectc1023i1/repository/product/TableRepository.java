package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TableRepository extends JpaRepository<Table,Integer> {
    @Query(value = " SELECT t.table_id , t.table_name , t.table_code, t.status FROM`table` AS t ; ",nativeQuery = true)
    Page<Table> findAllTable(Pageable pageable);

      @Query(value = "SELECT t.table_id, t.table_name, t.table_code, t.status FROM `table` AS t WHERE t.table_code = table_code", nativeQuery = true)
      Page<Table> findTableByCode(@Param("table_code") String tableCode, Pageable pageable);


    @Query(value = "SELECT t.table_id , t.table_name , t.table_code, t.status FROM`table` AS t  where t.status = status ",nativeQuery = true)
    Page<Table> findTableByStatus(@Param("status") boolean status,Pageable pageable);

    @Query(value = "DELETE FROM Table t WHERE t.id = :tableId",nativeQuery = true)
    void deleteTableById(@Param("tableId") int tableId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `table`  SET status = ?2 WHERE table_id = ?1",nativeQuery = true)
    void updateTableById(@Param("tableId") int tableId, @Param("newStatus") boolean newStatus);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `table` (table_code, table_name, status) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void createTable(String tableCode, String tableName, boolean status);
}
