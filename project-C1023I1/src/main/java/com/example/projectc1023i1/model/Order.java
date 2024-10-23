package com.example.projectc1023i1.model;


import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int invoiceId;

    @Column(name = "data_create",columnDefinition = "DATE")
    private LocalDate dateCreate;
    @Column(name = "data_update",columnDefinition = "DATE")
    private LocalDate dateUpdate;
    @Column(name="quantity")
    private int quantity;
    @Column(name = "shipping_day",columnDefinition = "DATE")
    private LocalDate shippingDay;
    @Column(name = "total_money_order")
    private double totalMoney;

    @ManyToOne
    @JoinColumn(name = "table_id",referencedColumnName = "table_id")
    private com.example.projectc1023i1.model.product.Table table;
}