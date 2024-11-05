package com.example.projectc1023i1.Dto.product.bill;


import com.example.projectc1023i1.model.product.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Integer orderId;
    private LocalDateTime dayCreate;
    private String creatorName;
    private String tableName;
    private Double totalMoneyOrder;
    private List<String> productDetails;
    private Integer quantily;


    // Constructors, Getters and Setters

    public OrderDTO() {
    }

    public OrderDTO(Integer orderId, LocalDateTime dayCreate, String creatorName, String tableName, Double totalMoneyOrder, List<String> productDetails, Integer quantily) {
        this.orderId = orderId;
        this.dayCreate = dayCreate;
        this.creatorName = creatorName;
        this.tableName = tableName;
        this.totalMoneyOrder = totalMoneyOrder;
        this.productDetails = productDetails;
        this.quantily = quantily;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(LocalDateTime dayCreate) {
        this.dayCreate = dayCreate;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Double getTotalMoneyOrder() {
        return totalMoneyOrder;
    }

    public void setTotalMoneyOrder(Double totalMoneyOrder) {
        this.totalMoneyOrder = totalMoneyOrder;
    }

    public List<String> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<String> productDetails) {
        this.productDetails = productDetails;
    }

    public Integer getQuantily() {
        return quantily;
    }

    public void setQuantily(Integer quantily) {
        this.quantily = quantily;
    }
}