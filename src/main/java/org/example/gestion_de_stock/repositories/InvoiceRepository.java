package org.example.gestion_de_stock.repositories;

import org.example.gestion_de_stock.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}