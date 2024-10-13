package codegym.vn.iposproduct.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;

    @Column(nullable = false, length = 10)
    private String feedbackCode;

    @Column(nullable = false, length = 100)
    private String nameCustomer;

    @Column(nullable = false)
    private LocalDateTime dayCreate;

    @Column(nullable = false, length = 200)
    private String emailCustomer;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
}

