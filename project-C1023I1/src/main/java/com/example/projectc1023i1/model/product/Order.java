package com.example.projectc1023i1.model.product;

import com.example.projectc1023i1.model.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@jakarta.persistence.Table(name = "`order`")
public class Order {
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

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

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
}
