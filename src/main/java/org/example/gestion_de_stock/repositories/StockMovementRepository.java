package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findByProductId(Long productId);
    List<StockMovement> findByWarehouseId(Long warehouseId);
    List<StockMovement> findByProductIdAndWarehouseId(Long productId, Long warehouseId);
}