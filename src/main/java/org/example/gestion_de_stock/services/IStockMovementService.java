package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.StockMovement;

import java.util.List;

public interface IStockMovementService {
    StockMovement createStockMovement(StockMovement stockMovement);
    StockMovement getStockMovementById(Long id);
    List<StockMovement> getAllStockMovements();
    StockMovement updateStockMovement(Long id, StockMovement updatedMovement);
    void deleteStockMovement(Long id);
    void addStock(Long productId, Long warehouseId, int quantity);
}
