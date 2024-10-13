package codegym.vn.iposproduct.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(nullable = false, length = 30)
    private String productCode;

    @Column(nullable = false, length = 255)
    private String productName;

    @Column(nullable = false)
    private double productPrice;

    private String productImgUrl;

    @Column(nullable = false)
    private boolean productStatus;

    @Column(nullable = false)
    private LocalDateTime createDay;

    private LocalDateTime updateDay;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Getters and Setters
}

