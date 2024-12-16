package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Notification;

import java.util.List;

public interface INotificationService {
    public Notification createNotification(String message, Long userId);

    public List<Notification> getAllNotifications();
    public List<Notification> getNotificationsByUserId(Long userId) ;

    public Notification markAsRead(Long notificationId);

    public void deleteNotification(Long id) ;
}
