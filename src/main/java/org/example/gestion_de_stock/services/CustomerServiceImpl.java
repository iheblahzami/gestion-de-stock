package org.example.gestion_de_stock.services;

import org.example.gestion_de_stock.entities.Customer;
import org.example.gestion_de_stock.entities.Invoice;
import org.example.gestion_de_stock.entities.Order;
import org.example.gestion_de_stock.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    // Create a new customer
 public Customer createCustomer (Customer customer) {
     return customerRepository.save(customer);

    }

    // Get a customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Update an existing customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = getCustomerById(id);
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setAddress(updatedCustomer.getAddress());
        return customerRepository.save(customer);
    }

    // Delete a customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    //Get All Invoices for a Customer
    public List<Invoice> getCustomerInvoices (Long idCustomer) {
     Customer customer = getCustomerById(idCustomer);
    return customer.getInvoices();
    }

    //Get All Orders of a Customer
    public List<Order> getCustomerOrders(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return customer.getOrders();
    }
//Find Customers by Name
public List<Customer> findCustomersByName(String name) {
    return customerRepository.findByNameContainingIgnoreCase(name);
}
//Find Customers by Email
public Customer findCustomerByEmail(String email) {
    return customerRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
}

}

