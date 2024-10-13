package codegym.vn.iposproduct.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(nullable = false, length = 30)
    private String roleName;

    // Getters and Setters
}
