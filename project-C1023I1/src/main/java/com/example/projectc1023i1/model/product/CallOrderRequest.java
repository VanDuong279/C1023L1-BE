package com.example.projectc1023i1.model.product;

import com.example.projectc1023i1.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@jakarta.persistence.Table(name = "call_order_request")  // Đặt tên bảng phù hợp
public class CallOrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "callOrderRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails> orderDetailsList = new ArrayList<>();

    // Quan hệ một-một với Table
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "table_id") // Chỉ rõ khóa ngoại
    private Table table;

    // Quan hệ một-một với User (nếu cần)
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id") // Chỉ rõ khóa ngoại
    private Users user;

}
