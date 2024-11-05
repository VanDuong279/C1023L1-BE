package com.example.projectc1023i1.service.product;

import com.example.projectc1023i1.model.product.Notification;

import java.util.List;
public interface NotificationService {
    List<Notification> findUnreadNotifications();
}
