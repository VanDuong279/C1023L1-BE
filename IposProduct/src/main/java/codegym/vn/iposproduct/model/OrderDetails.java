package codegym.vn.iposproduct.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double totalMoneyProduct;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Getters and Setters
}
