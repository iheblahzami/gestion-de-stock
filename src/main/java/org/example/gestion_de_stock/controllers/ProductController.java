package org.example.gestion_de_stock.controllers;

import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getProductByCategoryId(Long categoryId) {
        return productService.findByCategoryId(categoryId);
    }
    @GetMapping
    public List<Product> getProductBySupplierId(Long supplierId) {
        return productService.findBySupplierId(supplierId);
    }
    @GetMapping
    public List<Product> getProductByWarehouseId(Long warehouseId) {
        return productService.findByWarehouseId(warehouseId);}

}
