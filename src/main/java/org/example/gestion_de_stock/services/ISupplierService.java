package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.entities.Supplier;

import java.util.List;

public interface ISupplierService {

    public Supplier createSupplier(Supplier supplier);

    public Supplier getSupplierById(Long id) ;

    public List<Supplier> getAllSuppliers() ;

    public Supplier updateSupplier(Long id, Supplier updatedSupplier) ;

    public void deleteSupplier(Long id);

    public List<Product>getProductsBySupplier(long supplierId) ;

    public List<Supplier> findSuppliersByName(String name);

    public  int countProductsBySupplier (long supplierId);
}
