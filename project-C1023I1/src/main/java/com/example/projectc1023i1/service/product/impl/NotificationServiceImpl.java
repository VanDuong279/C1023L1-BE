package com.example.projectc1023i1.service.product.impl;

import com.example.projectc1023i1.model.product.Notification;
import com.example.projectc1023i1.repository.product.NotificationRepository;
import com.example.projectc1023i1.service.product.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public List<Notification> findUnreadNotifications() {
        return notificationRepository.findUnreadNotifications();
    }
}
