package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.entities.Warehouse;

import java.util.List;

public interface IWarehouseService {
    Warehouse createWarehouse(Warehouse warehouse);
    Warehouse getWarehouseById(Long id);
    List<Warehouse> getAllWarehouses();
    Warehouse updateWarehouse(Long id, Warehouse updatedWarehouse);
    void deleteWarehouse(Long id);
    Warehouse addProductToWarehouse(Long warehouseId, Product product);
    void removeProductFromWarehouse(Long warehouseId, Long productId);
    double calculateInventoryValue(Long warehouseId);
    int getProductStockInWarehouse(Long warehouseId, Long productId);
    List<Warehouse> getWarehousesWithLowStock(int threshold);
    List<Warehouse> searchWarehouses(String keyword);

}
