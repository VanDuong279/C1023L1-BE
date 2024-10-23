package com.example.projectc1023i1.model.product;


import com.example.projectc1023i1.model.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@jakarta.persistence.Table(name = "table")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private int id;
    @Column(name = "table_code")
    private String code;
    @Column(name="status")
    private boolean status;
    @OneToMany(mappedBy = "table")
    private List<Order> orders;

}








