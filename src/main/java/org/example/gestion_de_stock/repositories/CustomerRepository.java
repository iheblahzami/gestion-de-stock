package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public List<Customer> findByNameContainingIgnoreCase(String name);
    Optional <Customer> findByEmail(String email);
}