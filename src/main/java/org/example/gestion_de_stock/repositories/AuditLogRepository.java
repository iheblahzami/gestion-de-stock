package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}