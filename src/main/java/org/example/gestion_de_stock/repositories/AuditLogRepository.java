package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    // Find audit logs by User ID
    List<AuditLog> findByUserId(Long userId);

    // Find audit logs by action type
    List<AuditLog> findByAction(String action);

}