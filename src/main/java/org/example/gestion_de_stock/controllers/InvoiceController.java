package org.example.gestion_de_stock.controllers;

import org.example.gestion_de_stock.entities.Invoice;
import org.example.gestion_de_stock.entities.InvoiceItem;
import org.example.gestion_de_stock.services.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImpl invoiceService;

    // Create a new invoice
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        return ResponseEntity.ok(createdInvoice);
    }

    // Get an invoice by ID
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        try {
            Invoice invoice = invoiceService.getInvoiceById(id);
            return ResponseEntity.ok(invoice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all invoices
    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    // Update an existing invoice
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(
            @PathVariable Long id,
            @RequestBody Invoice updatedInvoice) {
        try {
            Invoice invoice = invoiceService.updateInvoice(id, updatedInvoice);
            return ResponseEntity.ok(invoice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an invoice
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        try {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Add items to an invoice
    @PostMapping("/{id}/items")
    public ResponseEntity<Invoice> addItemsToInvoice(
            @PathVariable Long id,
            @RequestBody List<InvoiceItem> invoiceItems) {
        try {
            Invoice invoice = invoiceService.addItemsToInvoice(id, invoiceItems);
            return ResponseEntity.ok(invoice);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Calculate total amount for an invoice
    @PutMapping("/{id}/calculate-total")
    public ResponseEntity<Invoice> calculateTotalAmount(@PathVariable Long id) {
        try {
            Invoice invoice = invoiceService.calculateTotalAmount(id);
            return ResponseEntity.ok(invoice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Find invoices by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Invoice>> findInvoicesByCustomer(@PathVariable Long customerId) {
        List<Invoice> invoices = invoiceService.findInvoicesByCustomer(customerId);
        return ResponseEntity.ok(invoices);
    }
}
