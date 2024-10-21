package com.example.projectc1023i1.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    // Getters and Setters

//    public Integer getFeedbackId() {
//        return feedbackId;
//    }
//
//    public void setFeedbackId(Integer feedbackId) {
//        this.feedbackId = feedbackId;
//    }
//
//    public String getFeedbackCode() {
//        return feedbackCode;
//    }
//
//    public void setFeedbackCode(String feedbackCode) {
//        this.feedbackCode = feedbackCode;
//    }
//
//    public String getNameCustomer() {
//        return nameCustomer;
//    }
//
//    public void setNameCustomer(String nameCustomer) {
//        this.nameCustomer = nameCustomer;
//    }
//
//    public LocalDateTime getDayCreate() {
//        return dayCreate;
//    }
//
//    public void setDayCreate(LocalDateTime dayCreate) {
//        this.dayCreate = dayCreate;
//    }
//
//    public String getEmailCustomer() {
//        return emailCustomer;
//    }
//
//    public void setEmailCustomer(String emailCustomer) {
//        this.emailCustomer = emailCustomer;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
