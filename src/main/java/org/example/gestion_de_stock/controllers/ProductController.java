package org.example.gestion_de_stock.controllers;

import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    // Create or update a product
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    // Retrieve a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    // Find products by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProductsByName(@RequestParam String name) {
        List<Product> products = productService.getProductsByName(name);
        return ResponseEntity.ok(products);
    }

    // Find products by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> findByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = productService.findByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }

    // Find products by supplier
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<Product>> findBySupplierId(@PathVariable Long supplierId) {
        List<Product> products = productService.findBySupplierId(supplierId);
        return ResponseEntity.ok(products);
    }

    // Find products by warehouse
    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<Product>> findByWarehouseId(@PathVariable Long warehouseId) {
        List<Product> products = productService.findByWarehouseId(warehouseId);
        return ResponseEntity.ok(products);
    }
}

