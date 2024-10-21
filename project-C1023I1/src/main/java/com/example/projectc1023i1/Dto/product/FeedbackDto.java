package com.example.projectc1023i1.Dto.product;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class FeedbackDto {
    @NotBlank(message = "Mã phản hồi không được để trống")
    private String feedBackCode;

    @NotBlank(message = "Tên khách hàng không được để trống")
    @Size(max = 100, message = "Tên khách hàng không được vượt quá 100 ký tự")
    private String nameCustomer;

    private LocalDateTime dayCreate;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String emailCustomer;

    @NotBlank(message = "Nội dung phản hồi không được để trống")
    private String content;

    // Getters và Setters
    public String getFeedBackCode() {
        return feedBackCode;
    }

    public void setFeedBackCode(String feedBackCode) {
        this.feedBackCode = feedBackCode;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public LocalDateTime getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(LocalDateTime dayCreate) {
        this.dayCreate = dayCreate;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
