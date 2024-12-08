package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.AuditLog;
import org.example.gestion_de_stock.entities.User;
import org.example.gestion_de_stock.repositories.AuditLogRepository;
import org.example.gestion_de_stock.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditlogServiceImpl  implements AuditlogService {
    private AuditLogRepository auditLogRepository;
    private UserRepository userRepository;


    // Create a new audit log entry
    public AuditLog createAuditLog(String action, String performedBy, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("User with ID " + userId + " not found."));

        AuditLog auditLog = new AuditLog();
        auditLog.setAction(action);
        auditLog.setTimestamp(LocalDateTime.now());
        auditLog.setPerformedBy(performedBy);
        auditLog.setUser(user);
        return auditLogRepository.save(auditLog);
    }

    // Retrieve all audit logs
    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    // Retrieve audit logs for a specific user
    public List<AuditLog> getAuditLogsByUserId(Long userId) {
        return auditLogRepository.findByUserId(userId);
    }

    // Retrieve audit logs by action type
    public List<AuditLog> getAuditLogsByAction(String action) {
        return auditLogRepository.findByAction(action);
    }

    // Delete an audit log entry by ID
    public void deleteAuditLog(Long id) {
        if (auditLogRepository.existsById(id)) {
            auditLogRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("AuditLog with ID " + id + " not found.");
        }
    }
}
