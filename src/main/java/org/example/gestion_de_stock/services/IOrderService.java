package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Customer;
import org.example.gestion_de_stock.entities.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IOrderService {

    public Order createOrder(Order order, Long customerId) ;

    public Optional<Order> getOrderById(Long id) ;

    public List<Order> getAllOrders() ;

    public Order updateOrder(Long id, Order updatedOrder);

    public void deleteOrder(Long id) ;

    public List<Order> getOrdersByCustomerId(Long customerId) ;

    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) ;

    public List<Order> getOrdersByStatus(String status) ;
}
