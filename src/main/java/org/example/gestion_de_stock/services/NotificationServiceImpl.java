package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Notification;
import org.example.gestion_de_stock.entities.User;
import org.example.gestion_de_stock.repositories.NotificationRepository;
import org.example.gestion_de_stock.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements INotificationService{
    private  NotificationRepository notificationRepository;
    private UserRepository userRepository;

    // Create a new notification
    public Notification createNotification(String message, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setDate(LocalDateTime.now());
            notification.setRead(false);
            notification.setUser(userOptional.get());
            return notificationRepository.save(notification);
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }
    }

    // Retrieve all notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Retrieve all notifications for a specific user
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Mark a notification as read
    public Notification markAsRead(Long notificationId) {
        Optional<Notification> notificationOptional = notificationRepository.findById(notificationId);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setRead(true);
            return notificationRepository.save(notification);
        } else {
            throw new IllegalArgumentException("Notification with ID " + notificationId + " not found.");
        }
    }

    // Delete a notification
    public void deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Notification with ID " + id + " not found.");
        }
    }
}
