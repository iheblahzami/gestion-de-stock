package org.example.gestion_de_stock.services;


import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.entities.StockMovement;
import org.example.gestion_de_stock.repositories.StockMovementRepository;
import org.example.gestion_de_stock.repositories.ProductRepository;
import org.example.gestion_de_stock.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockMovementServiceImpl implements IStockMovementService{
@Autowired
    private  StockMovementRepository stockMovementRepository;
    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private  WarehouseRepository warehouseRepository;


    // Create a new stock movement
    public StockMovement createStockMovement(StockMovement stockMovement) {
        return stockMovementRepository.save(stockMovement);
    }

    // Get a stock movement by ID
    public StockMovement getStockMovementById(Long id) {
        return stockMovementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock Movement not found"));
    }

    // Get all stock movements
    public List<StockMovement> getAllStockMovements() {
        return stockMovementRepository.findAll();
    }

    // Update a stock movement
    public StockMovement updateStockMovement(Long id, StockMovement updatedMovement) {
        StockMovement stockMovement = getStockMovementById(id);
        stockMovement.setType(updatedMovement.getType());
        stockMovement.setQuantity(updatedMovement.getQuantity());
        stockMovement.setDate(updatedMovement.getDate());
        stockMovement.setProduct(updatedMovement.getProduct());
        stockMovement.setWarehouse(updatedMovement.getWarehouse());
        return stockMovementRepository.save(stockMovement);
    }

    // Delete a stock movement
    public void deleteStockMovement(Long id) {
        stockMovementRepository.deleteById(id);
    }
//Add Stock to a Product
    public void addStock(Long productId, Long warehouseId, int quantity) {
        StockMovement stockMovement = new StockMovement();
        stockMovement.setType("IN");
        stockMovement.setQuantity(quantity);
        stockMovement.setDate(LocalDateTime.now());
        stockMovement.setProduct(productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found")));
        stockMovement.setWarehouse(warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found")));
        stockMovementRepository.save(stockMovement);
    }

    //Remove Stock from a Product
    public void removeStock(Long productId, Long warehouseId, int quantity) {
        StockMovement stockMovement = new StockMovement();
        stockMovement.setType("OUT");
        stockMovement.setQuantity(quantity);
        stockMovement.setDate(LocalDateTime.now());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock to remove");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        stockMovement.setProduct(product);
        stockMovement.setWarehouse(warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found")));
        stockMovementRepository.save(stockMovement);
    }
//Get All Movements for a Product
    public List<StockMovement> getMovementsForProduct(Long productId) {
        return stockMovementRepository.findByProductId(productId);
    }
//Get All Movements for a Warehouse
    public List<StockMovement> getMovementsForWarehouse(Long warehouseId) {
        return stockMovementRepository.findByWarehouseId(warehouseId);
    }
    //Get Stock Balance for a Product: Calculate the net stock of a product in a warehouse
    public int getStockBalance(Long productId, Long warehouseId) {
        List<StockMovement> movements = stockMovementRepository.findByProductIdAndWarehouseId(productId, warehouseId);
        return movements.stream()
                .mapToInt(movement -> "IN".equals(movement.getType()) ? movement.getQuantity() : -movement.getQuantity())
                .sum();
    }

}
