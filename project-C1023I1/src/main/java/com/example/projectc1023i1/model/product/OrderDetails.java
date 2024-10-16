package com.example.projectc1023i1.model.product;

import com.example.projectc1023i1.model.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@jakarta.persistence.Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(nullable = false)
    private LocalDateTime dayCreate;

    @Column(nullable = false)
    private LocalDateTime dayUpdate;

    private int quantity;

    @Column(nullable = false)
    private LocalDateTime shippingDay;

    @Column(nullable = false)
    private double totalMoneyOrder;

    @Column(nullable = false)
    private String status; // Trạng thái đơn hàng

    @Column(nullable = false)
    private LocalDateTime callOrderTime; // Thời gian gọi món

    @Column(nullable = false)
    private LocalDateTime callServiceTime; // Thời gian gọi phục vụ

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    // Getters và Setters cho tất cả các thuộc tính

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(LocalDateTime dayCreate) {
        this.dayCreate = dayCreate;
    }

    public LocalDateTime getDayUpdate() {
        return dayUpdate;
    }

    public void setDayUpdate(LocalDateTime dayUpdate) {
        this.dayUpdate = dayUpdate;
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

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
