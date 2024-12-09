package org.example.gestion_de_stock.controllers;

import org.example.gestion_de_stock.entities.StockMovement;
import org.example.gestion_de_stock.services.IStockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {



        @Autowired
        private IStockMovementService stockMovementService;

        // Create a new stock movement
        @PostMapping
        public ResponseEntity<StockMovement> createStockMovement(@RequestBody StockMovement stockMovement) {
            StockMovement createdMovement = stockMovementService.createStockMovement(stockMovement);
            return ResponseEntity.ok(createdMovement);
        }

        // Get a stock movement by ID
        @GetMapping("/{id}")
        public ResponseEntity<StockMovement> getStockMovementById(@PathVariable Long id) {
            StockMovement stockMovement = stockMovementService.getStockMovementById(id);
            return ResponseEntity.ok(stockMovement);
        }

        // Get all stock movements
        @GetMapping
        public ResponseEntity<List<StockMovement>> getAllStockMovements() {
            List<StockMovement> stockMovements = stockMovementService.getAllStockMovements();
            return ResponseEntity.ok(stockMovements);
        }

        // Update a stock movement
        @PutMapping("/{id}")
        public ResponseEntity<StockMovement> updateStockMovement(@PathVariable Long id, @RequestBody StockMovement updatedMovement) {
            StockMovement stockMovement = stockMovementService.updateStockMovement(id, updatedMovement);
            return ResponseEntity.ok(stockMovement);
        }

        // Delete a stock movement
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteStockMovement(@PathVariable Long id) {
            stockMovementService.deleteStockMovement(id);
            return ResponseEntity.noContent().build();
        }

        // Add stock to a product
        @PostMapping("/add-stock")
        public ResponseEntity<Void> addStock(@RequestParam Long productId, @RequestParam Long warehouseId, @RequestParam int quantity) {
            stockMovementService.addStock(productId, warehouseId, quantity);
            return ResponseEntity.ok().build();
        }

        // Remove stock from a product
        @PostMapping("/remove-stock")
        public ResponseEntity<Void> removeStock(@RequestParam Long productId, @RequestParam Long warehouseId, @RequestParam int quantity) {
            stockMovementService.removeStock(productId, warehouseId, quantity);
            return ResponseEntity.ok().build();
        }

        // Get all movements for a product
        @GetMapping("/product/{productId}")
        public ResponseEntity<List<StockMovement>> getMovementsForProduct(@PathVariable Long productId) {
            List<StockMovement> movements = stockMovementService.getMovementsForProduct(productId);
            return ResponseEntity.ok(movements);
        }

        // Get all movements for a warehouse
        @GetMapping("/warehouse/{warehouseId}")
        public ResponseEntity<List<StockMovement>> getMovementsForWarehouse(@PathVariable Long warehouseId) {
            List<StockMovement> movements = stockMovementService.getMovementsForWarehouse(warehouseId);
            return ResponseEntity.ok(movements);
        }

        // Get stock balance for a product in a warehouse
        @GetMapping("/balance")
        public ResponseEntity<Integer> getStockBalance(@RequestParam Long productId, @RequestParam Long warehouseId) {
            int balance = stockMovementService.getStockBalance(productId, warehouseId);
            return ResponseEntity.ok(balance);
        }
}
