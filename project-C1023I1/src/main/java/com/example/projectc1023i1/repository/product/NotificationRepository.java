package com.example.projectc1023i1.repository.product;

import com.example.projectc1023i1.model.product.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    @Query("SELECT n FROM Notification n WHERE n.isRead = false")
    List<Notification> findUnreadNotifications();
}
