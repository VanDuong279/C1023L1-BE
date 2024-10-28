package com.example.projectc1023i1.Dto.product;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
public class OrderDetailsDto {
    private Integer tableId;
    private int quantity;
    private LocalDateTime shippingDay;
    private double totalMoneyOrder;
    private String status;
    private LocalDateTime callOrderTime;
    private LocalDateTime callServiceTime;
    private int userId;
    private int productId;  // Thêm productId

    // Getters và Setters cho tất cả các thuộc tính
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getShippingDay() {
        return shippingDay;
    }

    public void setShippingDay(LocalDateTime shippingDay) {
        this.shippingDay = shippingDay;
    }

    public double getTotalMoneyOrder() {
        return totalMoneyOrder;
    }

    public void setTotalMoneyOrder(double totalMoneyOrder) {
        this.totalMoneyOrder = totalMoneyOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCallOrderTime() {
        return callOrderTime;
    }

    public void setCallOrderTime(LocalDateTime callOrderTime) {
        this.callOrderTime = callOrderTime;
    }

    public LocalDateTime getCallServiceTime() {
        return callServiceTime;
    }

    public void setCallServiceTime(LocalDateTime callServiceTime) {
        this.callServiceTime = callServiceTime;
    }
}
