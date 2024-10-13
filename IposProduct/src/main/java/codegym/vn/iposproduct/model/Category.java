package codegym.vn.iposproduct.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(nullable = false, length = 30)
    private String categoryCode;

    @Column(nullable = false, length = 255)
    private String categoryName;

    private String categoryImgUrl;

    // Getters and Setters
}
