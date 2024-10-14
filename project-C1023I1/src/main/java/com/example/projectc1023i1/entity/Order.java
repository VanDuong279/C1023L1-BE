package com.example.projectc1023i1.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(nullable = false)
    private LocalDate dayCreate;

    @Column(nullable = false)
    private LocalDate dayUpdate;

    private Integer quantity;

    @Column(nullable = false)
    private LocalDate shippingDay;

    @Column(nullable = false)
    private Double totalMoneyOrder;

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "table_id")
    private TableEntity table;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

     //Getters and Setters

//    public Integer getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Integer orderId) {
//        this.orderId = orderId;
//    }
//
//    public LocalDate getDayCreate() {
//        return dayCreate;
//    }
//
//    public void setDayCreate(LocalDate dayCreate) {
//        this.dayCreate = dayCreate;
//    }
//
//    public LocalDate getDayUpdate() {
//        return dayUpdate;
//    }
//
//    public void setDayUpdate(LocalDate dayUpdate) {
//        this.dayUpdate = dayUpdate;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public LocalDate getShippingDay() {
//        return shippingDay;
//    }
//
//    public void setShippingDay(LocalDate shippingDay) {
//        this.shippingDay = shippingDay;
//    }
//
//    public Double getTotalMoneyOrder() {
//        return totalMoneyOrder;
//    }
//
//    public void setTotalMoneyOrder(Double totalMoneyOrder) {
//        this.totalMoneyOrder = totalMoneyOrder;
//    }
//
//    public TableEntity getTable() {
//        return table;
//    }
//
//    public void setTable(TableEntity table) {
//        this.table = table;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}

