package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findByNameContainingIgnoreCaseOrLocationContainingIgnoreCase(String name, String location);

}