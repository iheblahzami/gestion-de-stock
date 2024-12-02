package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

 List<Supplier> findByNameContainingIgnoreCase (String name);

}