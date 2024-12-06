package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Customer;
import org.example.gestion_de_stock.entities.Order;
import org.example.gestion_de_stock.repositories.CustomerRepository;
import org.example.gestion_de_stock.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;



    // Create a new order
    public Order createOrder(Order order, Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            order.setCustomer(customerOptional.get());
            order.setOrderDate(LocalDateTime.now());
            return orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Customer with ID " + customerId + " not found.");
        }
    }

    // Retrieve an order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Retrieve all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Update an order
    public Order updateOrder(Long id, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            existingOrder.setStatus(updatedOrder.getStatus());
            existingOrder.setItems(updatedOrder.getItems());
            return orderRepository.save(existingOrder);
        } else {
            throw new IllegalArgumentException("Order with ID " + id + " not found.");
        }
    }

    // Delete an order
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Order with ID " + id + " not found.");
        }
    }

    // Retrieve orders by customer ID
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    // Advanced: Retrieve all orders within a date range
    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    // Advanced: Retrieve all orders with a specific status
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}
