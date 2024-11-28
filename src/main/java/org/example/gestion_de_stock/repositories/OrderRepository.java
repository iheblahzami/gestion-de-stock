package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}