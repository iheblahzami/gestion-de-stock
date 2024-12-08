package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Order;
import org.example.gestion_de_stock.entities.OrderItem;
import org.example.gestion_de_stock.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IOrderItemService {
    public OrderItem createOrderItem(OrderItem orderItem, Long orderId, Long productId);

    public Optional<OrderItem> getOrderItemById(Long id) ;

    public List<OrderItem> getAllOrderItems() ;

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) ;

    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) ;

    public void deleteOrderItem(Long id) ;

    public double calculateTotalPrice(Long id) ;
}
