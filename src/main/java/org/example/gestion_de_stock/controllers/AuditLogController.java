package org.example.gestion_de_stock.controllers;


import org.example.gestion_de_stock.entities.AuditLog;
import org.example.gestion_de_stock.services.AuditlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditlogs")
public class AuditLogController {

    @Autowired
    private AuditlogServiceImpl auditLogService;

    // Create a new audit log entry
    @PostMapping
    public ResponseEntity<AuditLog> createAuditLog(
            @RequestParam String action,
            @RequestParam String performedBy,
            @RequestParam Long userId) {
        try {
            AuditLog auditLog = auditLogService.createAuditLog(action, performedBy, userId);
            return ResponseEntity.ok(auditLog);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve all audit logs
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllAuditLogs() {
        List<AuditLog> auditLogs = auditLogService.getAllAuditLogs();
        return ResponseEntity.ok(auditLogs);
    }

    // Retrieve audit logs by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AuditLog>> getAuditLogsByUserId(@PathVariable Long userId) {
        List<AuditLog> auditLogs = auditLogService.getAuditLogsByUserId(userId);
        return ResponseEntity.ok(auditLogs);
    }

    // Retrieve audit logs by action type
    @GetMapping("/action")
    public ResponseEntity<List<AuditLog>> getAuditLogsByAction(@RequestParam String action) {
        List<AuditLog> auditLogs = auditLogService.getAuditLogsByAction(action);
        return ResponseEntity.ok(auditLogs);
    }

    // Delete an audit log entry by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuditLog(@PathVariable Long id) {
        try {
            auditLogService.deleteAuditLog(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
