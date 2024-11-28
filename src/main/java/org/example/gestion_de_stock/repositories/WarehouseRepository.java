package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}