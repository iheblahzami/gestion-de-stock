package org.example.gestion_de_stock.controllers;

import org.example.gestion_de_stock.entities.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.gestion_de_stock.services.ISupplierService;
import org.springframework.web.bind.annotation.*;
import org.example.gestion_de_stock.entities.Product;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

        @Autowired
        private ISupplierService supplierService;

        // Create a new supplier
        @PostMapping
        public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
            Supplier createdSupplier = supplierService.createSupplier(supplier);
            return ResponseEntity.ok(createdSupplier);
        }

        // Get a supplier by ID
        @GetMapping("/{id}")
        public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
            Supplier supplier = supplierService.getSupplierById(id);
            return ResponseEntity.ok(supplier);
        }

        // Get all suppliers
        @GetMapping
        public ResponseEntity<List<Supplier>> getAllSuppliers() {
            List<Supplier> suppliers = supplierService.getAllSuppliers();
            return ResponseEntity.ok(suppliers);
        }

        // Update a supplier
        @PutMapping("/{id}")
        public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier updatedSupplier) {
            Supplier supplier = supplierService.updateSupplier(id, updatedSupplier);
            return ResponseEntity.ok(supplier);
        }

        // Delete a supplier
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
            supplierService.deleteSupplier(id);
            return ResponseEntity.noContent().build();
        }

        // Get products by supplier ID
        @GetMapping("/{supplierId}/products")
        public ResponseEntity<List<Product>> getProductsBySupplier(@PathVariable Long supplierId) {
            List<Product> products = supplierService.getProductsBySupplier(supplierId);
            return ResponseEntity.ok(products);
        }

        // Find suppliers by name
        @GetMapping("/search")
        public ResponseEntity<List<Supplier>> findSuppliersByName(@RequestParam String name) {
            List<Supplier> suppliers = supplierService.findSuppliersByName(name);
            return ResponseEntity.ok(suppliers);
        }

        // Count the number of products a supplier provides
        @GetMapping("/{supplierId}/product-count")
        public ResponseEntity<Integer> countProductsBySupplier(@PathVariable Long supplierId) {
            int count = supplierService.countProductsBySupplier(supplierId);
            return ResponseEntity.ok(count);
        }
}
