package org.example.gestion_de_stock.controllers;

import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.entities.Warehouse;
import org.example.gestion_de_stock.services.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.gestion_de_stock.services.WarehouseServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    // Create a new warehouse
    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse createdWarehouse = warehouseService.createWarehouse(warehouse);
        return ResponseEntity.ok(createdWarehouse);
    }

    // Get a warehouse by ID
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        return ResponseEntity.ok(warehouse);
    }

    // Get all warehouses
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    // Update a warehouse
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse updatedWarehouse) {
        Warehouse warehouse = warehouseService.updateWarehouse(id, updatedWarehouse);
        return ResponseEntity.ok(warehouse);
    }

    // Delete a warehouse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }

    // Get all products in a specific warehouse
    @GetMapping("/{warehouseId}/products")
    public ResponseEntity<List<Product>> getProductsInWarehouse(@PathVariable Long warehouseId) {
        List<Product> products = warehouseService.getProductsInWarehouse(warehouseId);
        return ResponseEntity.ok(products);
    }

    // Add a product to a warehouse
    @PostMapping("/{warehouseId}/products")
    public ResponseEntity<Warehouse> addProductToWarehouse(@PathVariable Long warehouseId, @RequestBody Product product) {
        Warehouse warehouse = warehouseService.addProductToWarehouse(warehouseId, product);
        return ResponseEntity.ok(warehouse);
    }

    // Remove a product from a warehouse
    @DeleteMapping("/{warehouseId}/products/{productId}")
    public ResponseEntity<Void> removeProductFromWarehouse(@PathVariable Long warehouseId, @PathVariable Long productId) {
        warehouseService.removeProductFromWarehouse(warehouseId, productId);
        return ResponseEntity.noContent().build();
    }

    // Calculate the total inventory value of a warehouse
    @GetMapping("/{warehouseId}/inventory-value")
    public ResponseEntity<Double> calculateInventoryValue(@PathVariable Long warehouseId) {
        double totalValue = warehouseService.calculateInventoryValue(warehouseId);
        return ResponseEntity.ok(totalValue);
    }

    // Check stock of a specific product in a warehouse
    @GetMapping("/{warehouseId}/products/{productId}/stock")
    public ResponseEntity<Integer> getProductStockInWarehouse(@PathVariable Long warehouseId, @PathVariable Long productId) {
        int stock = warehouseService.getProductStockInWarehouse(warehouseId, productId);
        return ResponseEntity.ok(stock);
    }

    // Find warehouses with low stock
    @GetMapping("/low-stock")
    public ResponseEntity<List<Warehouse>> getWarehousesWithLowStock(@RequestParam int threshold) {
        List<Warehouse> warehouses = warehouseService.getWarehousesWithLowStock(threshold);
        return ResponseEntity.ok(warehouses);
    }

    // Search for warehouses by name or location
    @GetMapping("/search")
    public ResponseEntity<List<Warehouse>> searchWarehouses(@RequestParam String keyword) {
        List<Warehouse> warehouses = warehouseService.searchWarehouses(keyword);
        return ResponseEntity.ok(warehouses);
    }
}
