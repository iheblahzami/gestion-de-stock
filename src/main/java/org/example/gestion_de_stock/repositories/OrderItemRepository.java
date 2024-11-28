package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}