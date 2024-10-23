package com.example.projectc1023i1.model;
import com.example.projectc1023i1.model.product.Table;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;


    private String roleName;

    public static String USER = "USER";
    public static String ADMIN = "ADMIN";
    public static String EMPLOYEE = "EMPLOYEE";
}