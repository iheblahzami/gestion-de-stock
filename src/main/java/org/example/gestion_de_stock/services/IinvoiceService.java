package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Invoice;
import org.example.gestion_de_stock.entities.InvoiceItem;

import java.time.LocalDateTime;
import java.util.List;

public interface IinvoiceService {
    public Invoice createInvoice(Invoice invoice) ;

    public Invoice getInvoiceById(Long id) ;

    public List<Invoice> getAllInvoices();

    public Invoice updateInvoice(Long id, Invoice updatedInvoice) ;

    public void deleteInvoice(Long id) ;

    public  Invoice addItemsToInvoice (long InvoiceId , List<InvoiceItem> invoiceItems) ;

    public Invoice calculateTotalAmount(Long invoiceId) ;

    public List<Invoice> findInvoicesByCustomer (long CustomerId) ;
}
