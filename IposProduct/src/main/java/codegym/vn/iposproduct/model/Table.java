package codegym.vn.iposproduct.model;

import jakarta.persistence.*;

@Entity
@jakarta.persistence.Table(name = "`table`")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tableId;

    @Column(nullable = false, length = 30)
    private String tableCode;

    @Column(nullable = false, length = 30)
    private String tableName;

    @Column(nullable = false)
    private boolean status;

    // Getters and Setters
}
