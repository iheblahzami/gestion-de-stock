package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

 Product saveProduct(Product product) ;

 List<Product> getAllProducts();



     Optional<Product> getProductById(Long id);

     List<Product> getProductsByName(String name);

 void deleteProductById(Long id);

 List<Product> findByCategoryId(Long categoryId);

 List<Product> findBySupplierId(Long supplierId);

 List<Product> findByWarehouseId(Long warehouseId);
}