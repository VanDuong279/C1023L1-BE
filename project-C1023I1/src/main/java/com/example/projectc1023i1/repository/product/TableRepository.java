package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableRepository extends JpaRepository<Table,Integer> {
    @Query(value = "select t.id as tableId, t.code as tableCode, t.status as tableStatus from Table as t",nativeQuery = true)
    List<Table> findAllTable();

    @Query(value = "select t.id as tableId, t.code as tableCode, t.status as tableStatus from Table as t where t.code like :table_code ",nativeQuery = true)
    Table findTableByCode(@Param("table_code") String tableCode);

    @Query(value = "select t.id as tableId, t.code as tableCode, t.status as tableStatus from Table as t where t.status = :status ",nativeQuery = true)
    List<Table> findTableByStatus(@Param("status") boolean status);

    @Query(value = "DELETE FROM Table t WHERE t.id = :tableId",nativeQuery = true)
    boolean deleteTableById(@Param("tableId") int tableId);


    @Query(value = "UPDATE Table t SET t.status = :newStatus WHERE t.id = :tableId",nativeQuery = true)
    boolean updateTableById(@Param("tableId") Long tableId, @Param("newStatus") boolean newStatus);

    @Query(value = "CREATE TABLE my_table (table_id INT PRIMARY KEY AUTO_INCREMENT, table_code NVARCHAR(30) NOT NULL, status BIT NOT NULL DEFAULT 1)", nativeQuery = true)
    void createTable();
}
