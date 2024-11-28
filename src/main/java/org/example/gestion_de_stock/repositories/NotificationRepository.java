package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}