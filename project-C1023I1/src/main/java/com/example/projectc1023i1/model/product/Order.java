package com.example.projectc1023i1.model.product;

import com.example.projectc1023i1.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@jakarta.persistence.Table(name = "`order`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer orderId;

    @Column(nullable = false)
    private LocalDateTime dayCreate;

    @Column(nullable = false)
    private LocalDateTime dayUpdate;

    private int quantity;

    @Column(nullable = false)
    private LocalDateTime shippingDay;

    @Column(nullable = false)
    private double totalMoneyOrder;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Users user;
    public void setDayCreate(LocalDateTime dayCreate) {
        this.dayCreate = dayCreate;
    }

    public void setDayUpdate(LocalDateTime dayUpdate) {
        this.dayUpdate = dayUpdate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setShippingDay(LocalDateTime shippingDay) {
        this.shippingDay = shippingDay;
    }

    public void setTotalMoneyOrder(double totalMoneyOrder) {
        this.totalMoneyOrder = totalMoneyOrder;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setUser(Users user) {
        this.user = user;
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

    public LocalDateTime getDayUpdate() {
        return dayUpdate;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getShippingDay() {
        return shippingDay;
    }

    public double getTotalMoneyOrder() {
        return totalMoneyOrder;
    }

    public Table getTable() {
        return table;
    }

    public Users getUser() {
        return user;
    }

}
