package com.example.projectc1023i1.model.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@jakarta.persistence.Table(name = "call_service_request")  // Đặt tên bảng phù hợp
public class CallServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String serviceRequest;

    // Quan hệ một-một với Table
    @OneToOne
    @JoinColumn(name = "table_id") // Chỉ rõ khóa ngoại
    private Table table;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(String serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
