package com.example.projectc1023i1.model.feedback;

import com.example.projectc1023i1.model.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;

    @Column(nullable = false)
    private String feedbackCode;

    @Column(nullable = false)
    private String nameCustomer;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dayCreate;

    @Column(nullable = false)
    private String emailCustomer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Feedback() {
    }

    public Feedback(Integer feedbackId, String feedbackCode, String nameCustomer, Date dayCreate, String emailCustomer, String content, String status, Users user) {
        this.feedbackId = feedbackId;
        this.feedbackCode = feedbackCode;
        this.nameCustomer = nameCustomer;
        this.dayCreate = dayCreate;
        this.emailCustomer = emailCustomer;
        this.content = content;
        this.status = status;
        this.user = user;
    }

}

