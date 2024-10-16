package com.example.projectc1023i1.model.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Đây là mô tả của lớp User
 * @author Hau
 * @since 2024-10-14
 */
@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id" ,nullable = false)
    private Integer userId;
    @Column(name = "full_name", nullable = false,length = 50)
    private String fullName;
    @Column(name = "numberPhone", nullable = false,length = 10)
    private String numberPhone;
    @Column(name = "img_url",nullable = true)
    private String imgUrl;
    @Column(name = "gen_der",nullable = true)
    private Boolean gender;
    @Column(name = "birthday",nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
    @Column(name = "email",nullable = false , length = 50)
    private String email;
    @Column(name = "creat_at" )
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @Column(name = "salary", nullable = false)
    private Double salary;
    @Column(name = "address",nullable = false,length = 50)
    private String address;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "user_name",nullable = false,length = 50)
    private String userName;
    @Column(name = "password",nullable = false,length = 255)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;
}
