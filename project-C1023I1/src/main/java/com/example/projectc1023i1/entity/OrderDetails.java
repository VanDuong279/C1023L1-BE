package com.example.projectc1023i1.entity;

import com.example.projectc1023i1.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderDetail_id")
    private Integer orderDetailId;

    @Column(nullable = false)
    private Integer quantily;

    @Column(nullable = false)
    private Double totalMoneyProduct;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    // Getters and Setters

//    public Integer getOrderDetailId() {
//        return orderDetailId;
//    }
//
//    public void setOrderDetailId(Integer orderDetailId) {
//        this.orderDetailId = orderDetailId;
//    }
//
//    public Integer getQuantily() {
//        return quantily;
//    }
//
//    public void setQuantily(Integer quantily) {
//        this.quantily = quantily;
//    }
//
//    public Double getTotalMoneyProduct() {
//        return totalMoneyProduct;
//    }
//
//    public void setTotalMoneyProduct(Double totalMoneyProduct) {
//        this.totalMoneyProduct = totalMoneyProduct;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
}
