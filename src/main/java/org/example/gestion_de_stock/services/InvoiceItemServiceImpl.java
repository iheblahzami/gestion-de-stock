package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.InvoiceItem;
import org.example.gestion_de_stock.entities.Product;
import org.example.gestion_de_stock.repositories.InvoiceItemRepository;
import org.example.gestion_de_stock.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceItemServiceImpl implements IinvoiceItemService {
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // Create a new InvoiceItem
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        Product product = productRepository.findById(invoiceItem.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Calculate total price
        float totalPrice = invoiceItem.getQuantity() * invoiceItem.getUnitPrice();
        invoiceItem.setTotalPrice(totalPrice);

        // Deduct quantity from product stock
        if (product.getQuantity() < invoiceItem.getQuantity()) {
            throw new RuntimeException("Insufficient stock for product: " + product.getName());
        }
        product.setQuantity(product.getQuantity() - invoiceItem.getQuantity());
        productRepository.save(product);

        return invoiceItemRepository.save(invoiceItem);
    }

    // Retrieve an InvoiceItem by ID
    public InvoiceItem getInvoiceItemById(Long id) {
        return invoiceItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("InvoiceItem not found"));
    }
    // Retrieve all InvoiceItems
    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemRepository.findAll();
    }
    // Update an InvoiceItem
    public InvoiceItem updateInvoiceItem(Long id, InvoiceItem updatedInvoiceItem) {
        InvoiceItem existingInvoiceItem = getInvoiceItemById(id);

        existingInvoiceItem.setQuantity(updatedInvoiceItem.getQuantity());
        existingInvoiceItem.setUnitPrice(updatedInvoiceItem.getUnitPrice());
        existingInvoiceItem.setTotalPrice(updatedInvoiceItem.getQuantity() * updatedInvoiceItem.getUnitPrice());

        return invoiceItemRepository.save(existingInvoiceItem);
    }

    // Delete an InvoiceItem by ID
    public void deleteInvoiceItem(Long id) {
        InvoiceItem invoiceItem = getInvoiceItemById(id);

        // Restore the product's quantity when deleting the invoice item
        Product product = productRepository.findById(invoiceItem.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setQuantity(product.getQuantity() + invoiceItem.getQuantity());
        productRepository.save(product);

        invoiceItemRepository.deleteById(id);
    }

    // Retrieve all InvoiceItems for a specific Invoice
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(Long invoiceId) {
        return invoiceItemRepository.findByInvoiceId(invoiceId);
    }
}
