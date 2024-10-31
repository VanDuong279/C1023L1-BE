package com.example.projectc1023i1.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@jakarta.persistence.Table(name = "`table`") // Đổi tên bảng nếu cần thiết
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sinh tự động khóa chính
    @Column(name = "table_id")
    private Integer id;

    @Column(name = "table_code", nullable = false) // Không được null nếu cần
    private String code;

    @Column(name = "table_name", nullable = false) // Không được null nếu cần
    private String tableName;

    @Column(name = "status", nullable = false)
    private boolean status;

    // One-to-one với CallServiceRequest
    @OneToOne(mappedBy = "table", cascade = CascadeType.ALL)
    private CallServiceRequest callServiceRequest;

    // One-to-one với CallOrderRequest
    @OneToOne(mappedBy = "table", cascade = CascadeType.ALL)
    private CallOrderRequest callOrderRequest;

    // One-to-many với OrderDetails
    @OneToMany(mappedBy = "table")
    @JsonIgnore // Nếu không cần serialize OrderDetails
    private List<OrderDetails> orderDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public String getTableCode() {
//        return tableCode;
//    }
//
//    public void setTableCode(String tableCode) {
//        this.tableCode = tableCode;
//    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CallServiceRequest getCallServiceRequest() {
        return callServiceRequest;
    }

    public void setCallServiceRequest(CallServiceRequest callServiceRequest) {
        this.callServiceRequest = callServiceRequest;
    }

    public CallOrderRequest getCallOrderRequest() {
        return callOrderRequest;
    }

    public void setCallOrderRequest(CallOrderRequest callOrderRequest) {
        this.callOrderRequest = callOrderRequest;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
