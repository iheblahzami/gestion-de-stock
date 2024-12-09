package org.example.gestion_de_stock.controllers;


import org.example.gestion_de_stock.entities.InvoiceItem;
import org.example.gestion_de_stock.services.InvoiceItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice-items")
public class InvoiceItemController {

    @Autowired
    private InvoiceItemServiceImpl invoiceItemService;

    // Create a new InvoiceItem
    @PostMapping
    public ResponseEntity<InvoiceItem> createInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        try {
            InvoiceItem createdInvoiceItem = invoiceItemService.createInvoiceItem(invoiceItem);
            return ResponseEntity.ok(createdInvoiceItem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve an InvoiceItem by ID
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItem> getInvoiceItemById(@PathVariable Long id) {
        try {
            InvoiceItem invoiceItem = invoiceItemService.getInvoiceItemById(id);
            return ResponseEntity.ok(invoiceItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Retrieve all InvoiceItems
    @GetMapping
    public ResponseEntity<List<InvoiceItem>> getAllInvoiceItems() {
        List<InvoiceItem> invoiceItems = invoiceItemService.getAllInvoiceItems();
        return ResponseEntity.ok(invoiceItems);
    }

    // Update an existing InvoiceItem
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceItem> updateInvoiceItem(
            @PathVariable Long id,
            @RequestBody InvoiceItem updatedInvoiceItem) {
        try {
            InvoiceItem invoiceItem = invoiceItemService.updateInvoiceItem(id, updatedInvoiceItem);
            return ResponseEntity.ok(invoiceItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an InvoiceItem by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoiceItem(@PathVariable Long id) {
        try {
            invoiceItemService.deleteInvoiceItem(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Retrieve all InvoiceItems for a specific Invoice
    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<List<InvoiceItem>> getInvoiceItemsByInvoiceId(@PathVariable Long invoiceId) {
        List<InvoiceItem> invoiceItems = invoiceItemService.getInvoiceItemsByInvoiceId(invoiceId);
        return ResponseEntity.ok(invoiceItems);
    }
}
