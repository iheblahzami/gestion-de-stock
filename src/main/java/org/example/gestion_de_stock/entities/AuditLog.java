package org.example.gestion_de_stock.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.gestion_de_stock.security.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private LocalDateTime timestamp;
    private String performedBy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
