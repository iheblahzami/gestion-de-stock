package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.InvoiceItem;
import org.example.gestion_de_stock.entities.Product;

import java.util.List;

public interface IinvoiceItemService {
    // Create a new InvoiceItem
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) ;

    // Retrieve an InvoiceItem by ID
    public InvoiceItem getInvoiceItemById(Long id) ;
    // Retrieve all InvoiceItems
    public List<InvoiceItem> getAllInvoiceItems() ;
    // Update an InvoiceItem
    public InvoiceItem updateInvoiceItem(Long id, InvoiceItem updatedInvoiceItem) ;

    // Delete an InvoiceItem by ID
    public void deleteInvoiceItem(Long id) ;

    // Retrieve all InvoiceItems for a specific Invoice
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(Long invoiceId) ;
}
