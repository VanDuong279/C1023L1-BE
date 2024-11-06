package com.example.projectc1023i1.model.product;

import com.example.projectc1023i1.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@jakarta.persistence.Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailId;

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

    @ManyToOne
    @JoinColumn(name = "call_order_request_id")
    private CallOrderRequest callOrderRequest;
    @Column(nullable = false)
    private LocalDateTime callOrderTime;
    @Column(nullable = false)
    private LocalDateTime callServiceTime; // Thời gian gọi phục vụ

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;
    @Getter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;



    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Getter
    @ManyToOne
    @JoinColumn(name = "order_id") // tên cột trong bảng
    private Order order;



    public OrderDetails() {
    }

}
