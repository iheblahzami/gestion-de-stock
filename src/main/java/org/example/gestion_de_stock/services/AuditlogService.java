package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.AuditLog;
import org.example.gestion_de_stock.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditlogService {
    public AuditLog createAuditLog(String action, String performedBy, Long userId);

    public List<AuditLog> getAllAuditLogs() ;

    public List<AuditLog> getAuditLogsByUserId(Long userId) ;

    public List<AuditLog> getAuditLogsByAction(String action) ;

    public void deleteAuditLog(Long id) ;
}
