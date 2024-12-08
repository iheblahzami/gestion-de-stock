package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Order;
import org.example.gestion_de_stock.entities.OrderItem;
import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.repositories.OrderItemRepository;
import org.example.gestion_de_stock.repositories.OrderRepository;
import org.example.gestion_de_stock.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrederItemServiceImpl implements IOrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;



    // Create a new OrderItem
    public OrderItem createOrderItem(OrderItem orderItem, Long orderId, Long productId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (orderOptional.isPresent() && productOptional.isPresent()) {
            orderItem.setOrder(orderOptional.get());
            orderItem.setProduct(productOptional.get());
            return orderItemRepository.save(orderItem);
        } else {
            throw new IllegalArgumentException("Order or Product not found.");
        }
    }

    // Retrieve an OrderItem by ID
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    // Retrieve all OrderItems
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Retrieve all OrderItems for a specific order
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    // Update an OrderItem
    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        Optional<OrderItem> existingOrderItemOptional = orderItemRepository.findById(id);

        if (existingOrderItemOptional.isPresent()) {
            OrderItem existingOrderItem = existingOrderItemOptional.get();
            existingOrderItem.setQuantity(updatedOrderItem.getQuantity());
            existingOrderItem.setUnitPrice(updatedOrderItem.getUnitPrice());
            return orderItemRepository.save(existingOrderItem);
        } else {
            throw new IllegalArgumentException("OrderItem with ID " + id + " not found.");
        }
    }

    // Delete an OrderItem
    public void deleteOrderItem(Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("OrderItem with ID " + id + " not found.");
        }
    }

    // Advanced: Calculate total price for an OrderItem
    public double calculateTotalPrice(Long id) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);

        if (orderItemOptional.isPresent()) {
            OrderItem orderItem = orderItemOptional.get();
            return orderItem.getQuantity() * orderItem.getUnitPrice();
        } else {
            throw new IllegalArgumentException("OrderItem with ID " + id + " not found.");
        }
    }
}
