package com.example.projectc1023i1.Dto.product;

import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class CallOrderRequestDto {
    private int tableId;
    private int userId;
    private List<OrderDetailsDto> orderDetails;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderDetailsDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsDto> orderDetails) {
        this.orderDetails = orderDetails;
    }
    // Getters và Setters
}

