package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.entities.Warehouse;
import org.example.gestion_de_stock.repositories.ProductRepository;
import org.example.gestion_de_stock.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements IWarehouseService{

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;

    // Create a new warehouse
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    // Get a warehouse by ID
    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
    }

    // Get all warehouses
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    // Update a warehouse
    public Warehouse updateWarehouse(Long id, Warehouse updatedWarehouse) {
        Warehouse warehouse = getWarehouseById(id);
        warehouse.setName(updatedWarehouse.getName());
        warehouse.setLocation(updatedWarehouse.getLocation());
        return warehouseRepository.save(warehouse);
    }

    // Delete a warehouse
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
    // Get all products in a specific warehouse
    public List<Product> getProductsInWarehouse(Long warehouseId) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        return warehouse.getProducts();
    }

    public Warehouse addProductToWarehouse(Long warehouseId, Product product) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        product.setWarehouse(warehouse); // Link the product to the warehouse
        productRepository.save(product); // Save product with updated warehouse info
        return warehouse;
    }


    public void removeProductFromWarehouse(Long warehouseId, Long productId) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getWarehouse().getId().equals(warehouseId)) {
            product.setWarehouse(null); // Unlink warehouse
            productRepository.save(product);
        }
    }
    public double calculateInventoryValue(Long warehouseId) {
        List<Product> products = getProductsInWarehouse(warehouseId);
        return products.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }
   // Check Warehouse Stock for a Specific Product
    public int getProductStockInWarehouse(Long warehouseId, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getWarehouse().getId().equals(warehouseId)) {
            return product.getQuantity();
        }
        return 0;
    }
    //Find Warehouses with Low Stock
    public List<Warehouse> getWarehousesWithLowStock(int threshold) {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses.stream()
                .filter(warehouse -> warehouse.getProducts().stream()
                        .mapToInt(Product::getQuantity)
                        .sum() < threshold)
                .toList();
    }
    //Search Warehouse by Location or Name
    public List<Warehouse> searchWarehouses(String keyword) {
        return warehouseRepository.findByNameContainingIgnoreCaseOrLocationContainingIgnoreCase(keyword, keyword);
    }


}
