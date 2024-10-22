package com.example.projectc1023i1.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(length = 30, nullable = false)
    private String productCode;

    @Column(length = 255, nullable = false)
    private String productName;

    @Column(nullable = false)
    private double productPrice;

    @Column(length = 255)
    private String productImgUrl;

    @Column(nullable = false)
    private boolean productStatus;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDay;

    @UpdateTimestamp
    private LocalDateTime updateDay;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    @JsonManagedReference
    private Category category;

    // Mối quan hệ với OrderDetails
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Tránh serialization vòng lặp
    private List<OrderDetails> orderDetailsList;
}
