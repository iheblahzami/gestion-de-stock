package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    // Create or update a product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    // Retrieve a product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Retrieve all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Delete a product by ID
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


    // Find products by category
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    // Find products by supplier
    public List<Product> findBySupplierId(Long supplierId) {
        return productRepository.findBySupplierId(supplierId);
    }

    // Find products by warehouse
    public List<Product> findByWarehouseId(Long warehouseId) {
        return productRepository.findByWarehouseId(warehouseId);
    }
}
