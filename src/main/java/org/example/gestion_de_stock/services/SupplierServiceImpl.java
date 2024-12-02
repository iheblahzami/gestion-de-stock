package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.entities.Supplier;
import org.example.gestion_de_stock.repositories.ProductRepository;
import org.example.gestion_de_stock.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SupplierServiceImpl implements ISupplierService{
    @Autowired
    private  SupplierRepository supplierRepository;
    @Autowired
    private  ProductRepository productRepository;



    // Create a new supplier
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Get a supplier by ID
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    // Get all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Update an existing supplier
    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        Supplier supplier = getSupplierById(id);
        supplier.setName(updatedSupplier.getName());
        supplier.setContactNumber(updatedSupplier.getContactNumber());
        supplier.setEmail(updatedSupplier.getEmail());
        supplier.setAddress(updatedSupplier.getAddress());
        return supplierRepository.save(supplier);
    }

    // Delete a supplier
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    //Get Products by Supplier
    public List<Product>getProductsBySupplier(long supplierId) {
        Supplier supplier = getSupplierById(supplierId);
        return supplier.getProducts();
    }
    //Find Suppliers by Name
    public List<Supplier> findSuppliersByName(String name) {
        return supplierRepository.findByNameContainingIgnoreCase(name);
    }
    //Count the Number of Products a Supplier Provides
public  int countProductsBySupplier (long supplierId) {
        Supplier supplier = getSupplierById(supplierId);
        return supplier.getProducts().size();
}

}
