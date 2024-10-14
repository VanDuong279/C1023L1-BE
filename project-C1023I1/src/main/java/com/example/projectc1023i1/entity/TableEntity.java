package com.example.projectc1023i1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`table`")
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Integer tableId;

    @Column(nullable = false, length = 30)
    private String tableCode;

    @Column(nullable = false, length = 30)
    private String tableName;

    @Column(nullable = false)
    private Boolean status = true;

    // Getters and Setters

//    public Integer getTableId() {
//        return tableId;
//    }
//
//    public void setTableId(Integer tableId) {
//        this.tableId = tableId;
//    }
//
//    public String getTableCode() {
//        return tableCode;
//    }
//
//    public void setTableCode(String tableCode) {
//        this.tableCode = tableCode;
//    }
//
//    public String getTableName() {
//        return tableName;
//    }
//
//    public void setTableName(String tableName) {
//        this.tableName = tableName;
//    }
//
//    public Boolean getStatus() {
//        return status;
//    }
//
//    public void setStatus(Boolean status) {
//        this.status = status;
//    }
}

