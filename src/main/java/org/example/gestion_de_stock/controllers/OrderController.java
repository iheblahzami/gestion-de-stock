package org.example.gestion_de_stock.controllers;

import org.example.gestion_de_stock.entities.Order;
import org.example.gestion_de_stock.services.IOrderService;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

        @Autowired
        private IOrderService orderService;

        // Create a new Order
        @PostMapping
        public ResponseEntity<Order> createOrder(
                @RequestBody Order order,
                @RequestParam Long customerId) {
            try {
                Order createdOrder = orderService.createOrder(order, customerId);
                return ResponseEntity.ok(createdOrder);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        // Retrieve an Order by ID
        @GetMapping("/{id}")
        public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
            Optional<Order> orderOptional = orderService.getOrderById(id);
            return orderOptional.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        // Retrieve all Orders
        @GetMapping
        public ResponseEntity<List<Order>> getAllOrders() {
            List<Order> orders = orderService.getAllOrders();
            return ResponseEntity.ok(orders);
        }

        // Update an Order
        @PutMapping("/{id}")
        public ResponseEntity<Order> updateOrder(
                @PathVariable Long id,
                @RequestBody Order updatedOrder) {
            try {
                Order updated = orderService.updateOrder(id, updatedOrder);
                return ResponseEntity.ok(updated);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        }

        // Delete an Order
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
            try {
                orderService.deleteOrder(id);
                return ResponseEntity.noContent().build();
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        }

        // Retrieve Orders by Customer ID
        @GetMapping("/customer/{customerId}")
        public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
            List<Order> orders = orderService.getOrdersByCustomerId(customerId);
            return ResponseEntity.ok(orders);
        }

        // Retrieve Orders within a Date Range
        @GetMapping("/date-range")
        public ResponseEntity<List<Order>> getOrdersByDateRange(
                @RequestParam LocalDateTime startDate,
                @RequestParam LocalDateTime endDate) {
            List<Order> orders = orderService.getOrdersByDateRange(startDate, endDate);
            return ResponseEntity.ok(orders);
        }

        // Retrieve Orders by Status
        @GetMapping("/status")
        public ResponseEntity<List<Order>> getOrdersByStatus(@RequestParam String status) {
            List<Order> orders = orderService.getOrdersByStatus(status);
            return ResponseEntity.ok(orders);
        }
}
