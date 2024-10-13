package codegym.vn.iposproduct.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, length = 50)
    private String fullName;

    @Column(nullable = false, length = 10)
    private String numberPhone;

    private String imgUrl;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false, length = 50)
    private String email;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Column(nullable = false)
    private double salary;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false, length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // Getters and Setters
}

