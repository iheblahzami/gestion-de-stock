package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}