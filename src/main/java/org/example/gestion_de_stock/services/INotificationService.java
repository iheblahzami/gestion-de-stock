package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Notification;
import org.example.gestion_de_stock.entities.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface INotificationService {
    public Notification createNotification(String message, Long userId);

    public List<Notification> getAllNotifications();
    public List<Notification> getNotificationsByUserId(Long userId) ;

    public Notification markAsRead(Long notificationId);

    public void deleteNotification(Long id) ;
}
