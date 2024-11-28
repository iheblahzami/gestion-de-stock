package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}