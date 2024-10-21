package com.example.projectc1023i1.model.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@jakarta.persistence.Table(name = "call_order_request")  // Đặt tên bảng phù hợp
public class CallOrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String orderDetails;

    // Quan hệ một-một với Table
    @OneToOne
    @JoinColumn(name = "table_id", referencedColumnName = "table_id")  // Khóa ngoại tham chiếu đến table_id
    private Table table;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
