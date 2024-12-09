package org.example.gestion_de_stock.controllers;

import org.example.gestion_de_stock.entities.OrderItem;
import org.example.gestion_de_stock.services.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {


        @Autowired
        private IOrderItemService orderItemService;

        // Create a new OrderItem
        @PostMapping
        public ResponseEntity<OrderItem> createOrderItem(
                @RequestBody OrderItem orderItem,
                @RequestParam Long orderId,
                @RequestParam Long productId) {
            OrderItem createdOrderItem = orderItemService.createOrderItem(orderItem, orderId, productId);
            return ResponseEntity.ok(createdOrderItem);
        }

        // Retrieve an OrderItem by ID
        @GetMapping("/{id}")
        public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
            Optional<OrderItem> orderItemOptional = orderItemService.getOrderItemById(id);
            if (orderItemOptional.isPresent()) {
                return ResponseEntity.ok(orderItemOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // Retrieve all OrderItems
        @GetMapping
        public ResponseEntity<List<OrderItem>> getAllOrderItems() {
            List<OrderItem> orderItems = orderItemService.getAllOrderItems();
            return ResponseEntity.ok(orderItems);
        }

        // Retrieve all OrderItems for a specific order
        @GetMapping("/order/{orderId}")
        public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Long orderId) {
            List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
            return ResponseEntity.ok(orderItems);
        }

        // Update an OrderItem
        @PutMapping("/{id}")
        public ResponseEntity<OrderItem> updateOrderItem(
                @PathVariable Long id,
                @RequestBody OrderItem updatedOrderItem) {
            try {
                OrderItem updated = orderItemService.updateOrderItem(id, updatedOrderItem);
                return ResponseEntity.ok(updated);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        }

        // Delete an OrderItem
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
            try {
                orderItemService.deleteOrderItem(id);
                return ResponseEntity.noContent().build();
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        }

        // Calculate the total price for an OrderItem
        @GetMapping("/{id}/total-price")
        public ResponseEntity<Double> calculateTotalPrice(@PathVariable Long id) {
            try {
                double totalPrice = orderItemService.calculateTotalPrice(id);
                return ResponseEntity.ok(totalPrice);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        }
}
