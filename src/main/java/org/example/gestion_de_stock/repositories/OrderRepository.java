package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(long customerId);
    List<Order>  findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Order>   findByStatus( String status);
}