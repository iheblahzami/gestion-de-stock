package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Customer;
import org.example.gestion_de_stock.entities.Invoice;
import org.example.gestion_de_stock.entities.Order;

import java.util.List;

public interface ICustomerService {
    public Customer createCustomer (Customer customer) ;

    public Customer getCustomerById(Long id) ;

    public List<Customer> getAllCustomers() ;

    public Customer updateCustomer(Long id, Customer updatedCustomer) ;

    public void deleteCustomer(Long id);

    public List<Invoice> getCustomerInvoices (Long idCustomer) ;

    public List<Order> getCustomerOrders(Long customerId);

    public List<Customer> findCustomersByName(String name) ;

    public Customer findCustomerByEmail(String email);
}
