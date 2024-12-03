package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Invoice;
import org.example.gestion_de_stock.entities.InvoiceItem;
import org.example.gestion_de_stock.repositories.InvoiceItemRepository;
import org.example.gestion_de_stock.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceServiceImpl {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    // Create a new invoice
    public Invoice createInvoice(Invoice invoice) {
        invoice.setInvoiceDate(LocalDateTime.now());
        return invoiceRepository.save(invoice);
    }

    // Get an invoice by ID
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
    }

    // Get all invoices
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    // Update an existing invoice
    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        Invoice invoice = getInvoiceById(id);
        invoice.setTotalAmount(updatedInvoice.getTotalAmount());
        invoice.setInvoiceItems(updatedInvoice.getInvoiceItems());
        return invoiceRepository.save(invoice);
    }

    // Delete an invoice
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    //Add Items to an Invoice
    public  Invoice addItemsToInvoice (long InvoiceId , List<InvoiceItem> invoiceItems) {
        Invoice invoice = getInvoiceById(InvoiceId);
        invoiceItems.forEach(invoiceItem -> {invoiceItem.setInvoice(invoice);});
        invoice.getInvoiceItems().addAll(invoiceItems);
        return invoiceRepository.save(invoice);
    }
    //Calculate Total Amount
    public Invoice calculateTotalAmount(Long invoiceId) {
        Invoice invoice = getInvoiceById(invoiceId);
        float total = invoice.getInvoiceItems().stream()
                .map(item -> item.getUnitPrice() * item.getQuantity())
                .reduce(0f, Float::sum);
        invoice.setTotalAmount(total);
        return invoiceRepository.save(invoice);
    }
//Find Invoices by Customer
    public List<Invoice> findInvoicesByCustomer (long CustomerId) {
        return invoiceRepository.findByCustomerId(CustomerId);
    }
}
